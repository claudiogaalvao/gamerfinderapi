package com.gamerfinder.gamerfinder.domain

import com.gamerfinder.gamerfinder.dtos.response.GameSummaryResponse

data class Game(
    val id: Int,
    val name: String,
    val bannerUrl: String,
    val logoUrl: String,
    val subscriptions: Int,
    val minSubscriptions: Int,
    val rooms: Int? = null,
    val modes: List<String>? = null,
    val ranks: List<String>? = null,
    val positions: List<String>? = null,
    val platforms: List<Platforms>? = null,
)

fun List<Game>.toSummaryResponse() = mapIndexed { index, game ->
    GameSummaryResponse(
        id = game.id,
        name = game.name,
        bannerUrl = game.bannerUrl,
        subscriptions = game.subscriptions,
        minSubscriptions = game.minSubscriptions,
        locked = game.subscriptions <= game.minSubscriptions,
        rooms = game.rooms,
        displayOrder = index + 1,
    )
}
