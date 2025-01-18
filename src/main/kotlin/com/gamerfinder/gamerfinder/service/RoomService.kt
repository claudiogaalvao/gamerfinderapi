package com.gamerfinder.gamerfinder.service

import com.gamerfinder.gamerfinder.domain.Room
import com.gamerfinder.gamerfinder.domain.toResponse
import com.gamerfinder.gamerfinder.dtos.input.CreateRequestInput
import com.gamerfinder.gamerfinder.dtos.input.CreateRoomInput
import com.gamerfinder.gamerfinder.dtos.input.UpdateRoomInput
import com.gamerfinder.gamerfinder.dtos.output.CreateRoomOutput
import com.gamerfinder.gamerfinder.dtos.output.RoomOutput
import com.gamerfinder.gamerfinder.dtos.output.UpdateRoomOutput
import com.gamerfinder.gamerfinder.exception.ResourceNotFoundException
import com.gamerfinder.gamerfinder.exception.RoomAlreadyExistsException
import com.gamerfinder.gamerfinder.repository.GameRepository
import com.gamerfinder.gamerfinder.repository.PlayerRepository
import com.gamerfinder.gamerfinder.repository.RoomRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class RoomService(
    private val gameRepository: GameRepository = GameRepository(),
    private val playerRepository: PlayerRepository = PlayerRepository(),
    private val roomRepository: RoomRepository = RoomRepository()
) {

    fun getRooms(gameId: Int): List<RoomOutput> {
        return roomRepository.getRooms(gameId).map { it.toResponse() }
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
        input: CreateRequestInput
    ) {
        // check if the room exists
        // check if the player is not the host
        // check if the player is not already in the room
        // check if the room is not full
        // check the last time the player requested to join the room
    }

}