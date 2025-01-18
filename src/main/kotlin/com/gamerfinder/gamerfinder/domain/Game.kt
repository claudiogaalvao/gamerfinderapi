package com.gamerfinder.gamerfinder.domain

data class Game(
    val id: Int,
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
