package com.gamerfinder.gamerfinder.repository

import com.gamerfinder.gamerfinder.domain.Room

class RoomRepository {

    private var rooms: List<Room> = listOf()

    fun getRooms(gameId: Long): List<Room> {
        return rooms.stream().filter { it.gameId == gameId }.toList()
    }

    fun saveRoom(room: Room): Long {
        rooms = rooms + room
        return 1
    }

    fun getById(roomId: Long): Room {
        return rooms.first { it.id == roomId }
    }

    fun updateRoom(updatedRoom: Room) {
        rooms = rooms.map { if (it.id == updatedRoom.id) updatedRoom else it }
    }

    fun deleteRoom(roomId: Long) {
        rooms = rooms.filter { it.id != roomId }
    }

    fun exists(roomId: Long): Boolean {
        return rooms.any { it.id == roomId }
    }

    fun existsByPlayerId(playerId: Long): Boolean {
        return rooms.any { it.playerHost.id == playerId }
    }

    fun getRoomByPlayerId(playerId: Long): Room {
        return rooms.first { it.playerHost.id == playerId }
    }

    fun isHost(roomId: Long, playerId: Long): Boolean {
        return rooms.any { it.id == roomId && it.playerHost.id == playerId }
    }

    fun isPlayerInRoom(roomId: Long, playerId: Long): Boolean {
        return rooms.any { room -> room.id == roomId && room.playersIdJoined.any { it == playerId } }
    }


}