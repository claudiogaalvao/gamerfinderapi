package com.gamerfinder.gamerfinder.model

data class Room(
    val id: Long? = null,
    val gameId: Int,
    val playerHost: Player,
    val description: String,
    val spots: Int,
    val mode: String,
    val ranks: List<String>
)
