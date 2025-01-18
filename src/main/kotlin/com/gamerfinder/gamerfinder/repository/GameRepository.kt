package com.gamerfinder.gamerfinder.repository

import com.gamerfinder.gamerfinder.domain.Game
import com.gamerfinder.gamerfinder.domain.Platforms


class GameRepository {

    fun getAll() = getGamesMock()

    fun getById(id: Int) = getGamesMock().first { it.id == id }

}

private fun getGamesMock() = listOf(
    Game(
        id = 1010,
        name = "Valorant",
        bannerUrl = "https://example.com/valorant-cover.jpg",
        logoUrl = "https://example.com/valorant-logo.jpg",
        subscriptions = 123,
        minSubscriptions = 100,
        modes = listOf("Ranked", "Casual"),
        ranks = listOf(
            "Iron",
            "Bronze",
            "Silver",
            "Gold",
            "Platinum",
            "Diamond",
            "Immortal",
            "Radiant"
        ),
        rooms = 5,
        positions = listOf("Duelist", "Initiator", "Controller", "Sentinel"),
        platforms = listOf(Platforms.PC, Platforms.PS4, Platforms.XBOX_ONE)
    ),
    Game(
        id = 2020,
        name = "Apex Legends",
        bannerUrl = "https://example.com/apex-legends-cover.jpg",
        logoUrl = "https://example.com/apex-legends-logo.jpg",
        subscriptions = 30,
        minSubscriptions = 60,
        modes = listOf("Ranked", "Casual"),
        ranks = listOf("Bronze", "Silver", "Gold", "Platinum", "Diamond", "Master", "Predator"),
        positions = listOf("Offense", "Defense", "Support"),
        platforms = listOf(Platforms.PC, Platforms.PS4, Platforms.XBOX_ONE, Platforms.SWITCH)
    ),
    Game(
        id = 3030,
        name = "Fortnite",
        bannerUrl = "https://example.com/fortnite-cover.jpg",
        logoUrl = "https://example.com/fortnite-logo.jpg",
        subscriptions = 40,
        minSubscriptions = 80,
        modes = listOf("Ranked", "Casual"),
        ranks = listOf("Bronze", "Silver", "Gold", "Platinum", "Diamond", "Master", "Champion"),
        positions = listOf("Offense", "Defense", "Support"),
        platforms = listOf(
            Platforms.PC,
            Platforms.PS4,
            Platforms.XBOX_ONE,
            Platforms.SWITCH,
            Platforms.PS4
        )
    )
)