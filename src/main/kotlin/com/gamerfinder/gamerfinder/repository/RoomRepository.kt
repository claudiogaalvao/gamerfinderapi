package com.gamerfinder.gamerfinder.repository

import com.gamerfinder.gamerfinder.model.Room

class RoomRepository {

    private var rooms: List<Room> = listOf()

    fun getRooms(gameId: Int): List<Room> {
        return rooms.stream().filter { it.gameId == gameId }.toList()
    }

    fun saveRoom(room: Room) {
        rooms = rooms + room
    }
}