package com.gamerfinder.gamerfinder.service

import com.gamerfinder.gamerfinder.dtos.request.CreateRoomRequest
import com.gamerfinder.gamerfinder.domain.Room
import com.gamerfinder.gamerfinder.domain.toResponse
import com.gamerfinder.gamerfinder.dtos.request.UpdateRoomRequest
import com.gamerfinder.gamerfinder.repository.GameRepository
import com.gamerfinder.gamerfinder.repository.PlayerRepository
import com.gamerfinder.gamerfinder.repository.RoomRepository
import com.gamerfinder.gamerfinder.dtos.response.CreateRoomResponse
import com.gamerfinder.gamerfinder.dtos.response.RoomResponse
import com.gamerfinder.gamerfinder.dtos.response.UpdateRoomResponse
import com.gamerfinder.gamerfinder.exception.ResourceNotFoundException
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

    fun getRooms(gameId: Int): List<RoomResponse> {
        return roomRepository.getRooms(gameId).map { it.toResponse() }
    }

    fun createRoom(
        gameId: Int,
        playerId: Int,
        request: CreateRoomRequest
    ): CreateRoomResponse {
        // check if the player already have a room created
        val player = playerRepository.getById(playerId)
        val room = Room(
            id = UUID.randomUUID().toString(),
            gameId = gameId,
            playerHost = player,
            description = request.description,
            spots = request.spots,
            mode = request.mode,
            ranks = request.ranks,
            createdAt = LocalDateTime.now()
        )
        roomRepository.saveRoom(room)
        return CreateRoomResponse(
            id = room.id
        )
    }

    fun update(roomId: String, request: UpdateRoomRequest): UpdateRoomResponse {
        if (roomRepository.exists(roomId).not()) {
            throw ResourceNotFoundException("Room with id $roomId not found!")
        }
        val roomToUpdate = roomRepository.getById(roomId)
        val room = roomToUpdate.copy(
            description = request.description,
            spots = request.spots,
            mode = request.mode,
            ranks = request.ranks
        )
        roomRepository.updateRoom(room)
        return UpdateRoomResponse(
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

}