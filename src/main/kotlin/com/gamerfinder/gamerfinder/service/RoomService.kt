package com.gamerfinder.gamerfinder.service

import com.gamerfinder.gamerfinder.domain.JoinRequest
import com.gamerfinder.gamerfinder.domain.Room
import com.gamerfinder.gamerfinder.domain.enums.JoinRequestStatus
import com.gamerfinder.gamerfinder.domain.toOutput
import com.gamerfinder.gamerfinder.dtos.input.CreateJoinRequestInput
import com.gamerfinder.gamerfinder.dtos.input.CreateRoomInput
import com.gamerfinder.gamerfinder.dtos.input.UpdateRoomInput
import com.gamerfinder.gamerfinder.dtos.output.CreateJoinRequestOutput
import com.gamerfinder.gamerfinder.dtos.output.CreateRoomOutput
import com.gamerfinder.gamerfinder.dtos.output.PendingJoinRequestOutput
import com.gamerfinder.gamerfinder.dtos.output.RoomOutput
import com.gamerfinder.gamerfinder.dtos.output.UpdateRoomOutput
import com.gamerfinder.gamerfinder.exception.ResourceNotFoundException
import com.gamerfinder.gamerfinder.exception.RoomAlreadyExistsException
import com.gamerfinder.gamerfinder.repository.GameRepository
import com.gamerfinder.gamerfinder.repository.JoinRequestRepository
import com.gamerfinder.gamerfinder.repository.PlayerRepository
import com.gamerfinder.gamerfinder.repository.RoomRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class RoomService(
    private val roomRepository: RoomRepository = RoomRepository(),
    private val gameRepository: GameRepository = GameRepository(),
    private val playerRepository: PlayerRepository = PlayerRepository(),
    private val joinRequestRepository: JoinRequestRepository = JoinRequestRepository()
) {

    fun getRooms(gameId: Int): List<RoomOutput> {
        return roomRepository.getRooms(gameId).map { it.toOutput() }
    }

    fun createRoom(
        gameId: Int,
        playerId: Int,
        input: CreateRoomInput
    ): CreateRoomOutput {
        if (roomRepository.existsByPlayerId(playerId)) {
            throw RoomAlreadyExistsException("Player with id $playerId already have a room created!")
        }
        val player = playerRepository.getById(playerId)
        val room = Room(
            id = UUID.randomUUID().toString(),
            gameId = gameId,
            playerHost = player,
            description = input.description,
            spots = input.spots,
            mode = input.mode,
            ranks = input.ranks,
            createdAt = LocalDateTime.now()
        )
        roomRepository.saveRoom(room)
        return CreateRoomOutput(
            id = room.id
        )
    }

    fun update(roomId: String, input: UpdateRoomInput): UpdateRoomOutput {
        if (roomRepository.exists(roomId).not()) {
            throw ResourceNotFoundException("Room with id $roomId not found!")
        }
        val roomToUpdate = roomRepository.getById(roomId)
        val room = roomToUpdate.copy(
            description = input.description,
            spots = input.spots,
            mode = input.mode,
            ranks = input.ranks
        )
        roomRepository.updateRoom(room)
        return UpdateRoomOutput(
            description = room.description,
            spots = room.spots,
            mode = room.mode,
            ranks = room.ranks
        )
    }

    fun delete(roomId: String): ResponseEntity<Any> {
        if (roomRepository.exists(roomId).not()) {
            throw ResourceNotFoundException("Room with id $roomId not found!")
        }
        roomRepository.deleteRoom(roomId)
        return ResponseEntity.noContent().build()
    }

    fun requestToJoinRoom(
        roomId: String,
        input: CreateJoinRequestInput
    ): CreateJoinRequestOutput {
        if (roomRepository.exists(roomId).not()) {
            throw ResourceNotFoundException("Room with id $roomId not found!")
        }
        if (roomRepository.isHost(roomId, input.playerId)) {
            throw IllegalArgumentException("Player with id ${input.playerId} is the host of the room!")
        }
        if (roomRepository.isPlayerInRoom(roomId, input.playerId)) {
            throw IllegalArgumentException("Player with id ${input.playerId} is already in the room!")
        }
        if (roomRepository.getById(roomId).spots == 0) {
            throw IllegalArgumentException("Room with id $roomId is full!")
        }
        if (joinRequestRepository.existsPendingRequest(input.playerId)) {
            throw IllegalArgumentException("Player with id ${input.playerId} already have a pending request to join the room!")
        }
        if (joinRequestRepository.existsRejectedRequest(roomId, input.playerId)) {
            throw IllegalArgumentException("Player with id ${input.playerId} was rejected to join the room!")
        }
        val position = joinRequestRepository.getPlayersInQueueCount(roomId) + 1
        val joinRequest = JoinRequest(
            id = UUID.randomUUID().toString(),
            roomId = roomId,
            playerId = input.playerId,
            status = JoinRequestStatus.PENDING,
            queuePosition = position,
            createdAt = LocalDateTime.now()
        )
        joinRequestRepository.saveJoinRequest(joinRequest)
        return CreateJoinRequestOutput(
            id = joinRequest.id,
            queuePosition = joinRequest.queuePosition
        )
    }

    fun getPendingJoinRequests(roomId: String): List<PendingJoinRequestOutput> {
        val pendingJoinRequests = joinRequestRepository.getPendingJoinRequestsByRoomId(roomId)
        return pendingJoinRequests.toOutput()
    }

    fun acceptJoinRequest(roomId: String, requestId: String) {
        // check if room exists
        // check if request exists
        // check if request is pending
        // check if room is full
        // check if player is already in the room
        // check if player is already in the queue
        // check if player is in another room
        // check if accept is from the host
    }

}