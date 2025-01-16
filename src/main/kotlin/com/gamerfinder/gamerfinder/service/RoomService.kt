package com.gamerfinder.gamerfinder.service

import com.gamerfinder.gamerfinder.model.Game
import com.gamerfinder.gamerfinder.model.Platforms
import com.gamerfinder.gamerfinder.model.Player
import com.gamerfinder.gamerfinder.model.Room
import org.springframework.stereotype.Service

@Service
class RoomService(
    private var rooms: List<Room> = listOf(),
) {
    init {
        val game = Game(
            id = 1010,
            name = "Valorant",
            coverUrl = "https://example.com/valorant-cover.jpg",
            logoUrl = "https://example.com/valorant-logo.jpg",
            subscriptions = 52,
            minSubscriptions = 100,
            modes = listOf("Ranked", "Casual"),
            ranks = listOf("Iron", "Bronze", "Silver", "Gold", "Platinum", "Diamond", "Immortal", "Radiant"),
            positions = listOf("Duelist", "Initiator", "Controller", "Sentinel"),
            platforms = listOf(Platforms.PC, Platforms.PS4, Platforms.XBOX_ONE)
        )
        val room1 = Room(
            id = 1,
            gameId = 1010,
            playerHost = Player("John Doe", "https://example.com/johndoe.jpg"),
            description = "Looking for a team to play ranked",
            spots = 4,
            mode = "Ranked",
            ranks = listOf("Platinum", "Diamond")
        )
        val room2 = Room(
            id = 2,
            gameId = 2020,
            playerHost = Player("Jane Doe", "https://example.com/janedoe.jpg"),
            description = "Looking for a team to play casual",
            spots = 3,
            mode = "Casual",
            ranks = listOf("Gold", "Platinum")
        )
        val room3 = Room(
            id = 3,
            gameId = 1010,
            playerHost = Player("Julia Fox", "https://example.com/janedoe.jpg"),
            description = "Looking for a team to play casual",
            spots = 3,
            mode = "Casual",
            ranks = listOf("")
        )
        rooms = listOf(room1, room2, room3)
    }

    fun getRooms(gameId: Int): List<Room> {
        return rooms.stream().filter { it.gameId == gameId }.toList()
    }

}