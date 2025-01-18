package com.gamerfinder.gamerfinder.repository

import com.gamerfinder.gamerfinder.domain.Room

class RoomRepository {

    private var rooms: List<Room> = listOf()

    fun getRooms(gameId: Int): List<Room> {
        return rooms.stream().filter { it.gameId == gameId }.toList()
    }

    fun saveRoom(room: Room) {
        rooms = rooms + room
    }

    fun getById(roomId: String): Room {
        return rooms.first { it.id == roomId }
    }

    fun updateRoom(updatedRoom: Room) {
        rooms = rooms.map { if (it.id == updatedRoom.id) updatedRoom else it }
    }

    fun deleteRoom(roomId: String) {
        rooms = rooms.filter { it.id != roomId }
    }

    fun exists(roomId: String): Boolean {
        return rooms.any { it.id == roomId }
    }

    fun existsByPlayerId(playerId: Int): Boolean {
        return rooms.any { it.playerHost.id == playerId }
    }

    fun getRoomByPlayerId(playerId: Int): Room {
        return rooms.first { it.playerHost.id == playerId }
    }
}