package com.gamerfinder.gamerfinder.model

import java.time.LocalDateTime

data class Room(
    val id: String,
    val gameId: Int,
    val playerHost: Player,
    val description: String,
    val spots: Int,
    val mode: String,
    val ranks: List<String>,
    val createdAt: LocalDateTime,
)
