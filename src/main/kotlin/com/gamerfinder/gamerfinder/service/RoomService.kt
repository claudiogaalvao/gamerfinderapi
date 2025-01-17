package com.gamerfinder.gamerfinder.service

import com.gamerfinder.gamerfinder.dto.RoomDto
import com.gamerfinder.gamerfinder.model.Room
import com.gamerfinder.gamerfinder.repository.GameRepository
import com.gamerfinder.gamerfinder.repository.PlayerRepository
import com.gamerfinder.gamerfinder.repository.RoomRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class RoomService(
    private val gameRepository: GameRepository = GameRepository(),
    private val playerRepository: PlayerRepository = PlayerRepository(),
    private val roomRepository: RoomRepository = RoomRepository()
) {

    fun getRooms(gameId: Int): List<Room> {
        return roomRepository.getRooms(gameId)
    }

    fun createRoom(
        gameId: Int,
        playerId: Int,
        room: RoomDto
    ) {
        val player = playerRepository.getById(playerId)
        val newRoom = Room(
            id = UUID.randomUUID().toString(),
            gameId = gameId,
            playerHost = player,
            description = room.description,
            spots = room.spots,
            mode = room.mode,
            ranks = room.ranks,
            createdAt = LocalDateTime.now()
        )
        roomRepository.saveRoom(newRoom)
    }

}