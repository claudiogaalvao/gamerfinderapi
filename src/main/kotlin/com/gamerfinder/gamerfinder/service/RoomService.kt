package com.gamerfinder.gamerfinder.service

import com.gamerfinder.gamerfinder.constants.MAX_ROOM_TIME_IN_MINUTES
import com.gamerfinder.gamerfinder.domain.JoinRequest
import com.gamerfinder.gamerfinder.domain.Room
import com.gamerfinder.gamerfinder.domain.enums.JoinRequestStatus
import com.gamerfinder.gamerfinder.domain.toOutput
import com.gamerfinder.gamerfinder.dtos.input.CreateJoinRequestInput
import com.gamerfinder.gamerfinder.dtos.input.CreateRoomInput
import com.gamerfinder.gamerfinder.dtos.input.UpdateRoomInput
import com.gamerfinder.gamerfinder.dtos.output.CreateJoinRequestOutput
import com.gamerfinder.gamerfinder.dtos.output.CreateRoomOutput
import com.gamerfinder.gamerfinder.dtos.output.RoomOutput
import com.gamerfinder.gamerfinder.dtos.output.UpdateRoomOutput
import com.gamerfinder.gamerfinder.exception.ResourceNotFoundException
import com.gamerfinder.gamerfinder.exception.RoomAlreadyExistsException
import com.gamerfinder.gamerfinder.repository.GameRepository
import com.gamerfinder.gamerfinder.repository.JoinRequestRepository
import com.gamerfinder.gamerfinder.repository.PlayerRepository
import com.gamerfinder.gamerfinder.repository.RoomRepository
import com.gamerfinder.gamerfinder.repository.RoomRepositoryMock
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RoomService(
    private val roomRepository: RoomRepository,
    private val roomRepositoryMock: RoomRepositoryMock = RoomRepositoryMock(),
    private val gameRepository: GameRepository,
    private val playerRepository: PlayerRepository,
    private val joinRequestRepository: JoinRequestRepository = JoinRequestRepository()
) {

    fun getRoomsCreatedInLast30Minutes(gameId: Long): List<RoomOutput> {
        val thirtyMinutesAgo = LocalDateTime.now().minusMinutes(MAX_ROOM_TIME_IN_MINUTES)
        return roomRepository
            .findByGameIdAndCreatedAtAfter(gameId, thirtyMinutesAgo)
            .map { it.toOutput() }
    }

    fun createRoom(
        gameId: Long,
        playerId: Long,
        input: CreateRoomInput
    ): CreateRoomOutput {
        if (roomRepository.existsById(playerId)) {
            throw RoomAlreadyExistsException("Player with id $playerId already have a room created!")
        }
        val player = playerRepository.findById(playerId).orElseThrow {
            ResourceNotFoundException("Player with id $playerId not found!")
        }
        val room = Room(
            gameId = gameId,
            playerHost = player,
            description = input.description,
            spots = input.spots,
            mode = input.mode,
            ranks = input.ranks
        )
        val savedRoom = roomRepository.save(room)
        if (savedRoom.id == null) {
            throw IllegalArgumentException("Something went wrong when try to save room!")
        }
        return CreateRoomOutput(
            id = savedRoom.id
        )
    }

    fun update(roomId: Long, input: UpdateRoomInput): UpdateRoomOutput {
        val roomToUpdate = roomRepository.findById(roomId).orElseThrow {
            ResourceNotFoundException("Room with id $roomId not found!")
        }
        roomToUpdate.description = input.description
        roomToUpdate.spots = input.spots
        roomToUpdate.mode = input.mode
        roomToUpdate.ranks = input.ranks
        return UpdateRoomOutput(
            description = roomToUpdate.description,
            spots = roomToUpdate.spots,
            mode = roomToUpdate.mode,
            ranks = roomToUpdate.ranks
        )
    }

    fun delete(roomId: Long): ResponseEntity<Any> {
        if (roomRepository.existsById(roomId).not()) {
            throw ResourceNotFoundException("Room with id $roomId not found!")
        }
        roomRepository.deleteById(roomId)
        return ResponseEntity.noContent().build()
    }

    fun requestToJoinRoom(
        roomId: Long,
        input: CreateJoinRequestInput
    ): CreateJoinRequestOutput {
        if (roomRepositoryMock.exists(roomId).not()) {
            throw ResourceNotFoundException("Room with id $roomId not found!")
        }
        if (roomRepositoryMock.isHost(roomId, input.playerId)) {
            throw IllegalArgumentException("Player with id ${input.playerId} is the host of the room!")
        }
        if (roomRepositoryMock.isPlayerInRoom(roomId, input.playerId)) {
            throw IllegalArgumentException("Player with id ${input.playerId} is already in the room!")
        }
        if (roomRepositoryMock.getById(roomId).spots == 0) {
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
            roomId = roomId,
            playerId = input.playerId,
            status = JoinRequestStatus.PENDING,
            queuePosition = position,
            createdAt = LocalDateTime.now()
        )
        val joinRequestId = joinRequestRepository.saveJoinRequest(joinRequest)
        return CreateJoinRequestOutput(
            id = joinRequestId,
            queuePosition = joinRequest.queuePosition
        )
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