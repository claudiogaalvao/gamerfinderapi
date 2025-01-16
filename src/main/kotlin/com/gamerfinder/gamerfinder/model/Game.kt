package com.gamerfinder.gamerfinder.model

data class Game(
    val id: Long,
    val name: String,
    val coverUrl: String,
    val logoUrl: String,
    val subscriptions: Int? = null,
    val minSubscriptions: Int? = null,
    val openRooms: Int? = null,
    val modes: List<String>? = null,
    val ranks: List<String>? = null,
    val positions: List<String>? = null,
    val platforms: List<Platforms>? = null,
)
