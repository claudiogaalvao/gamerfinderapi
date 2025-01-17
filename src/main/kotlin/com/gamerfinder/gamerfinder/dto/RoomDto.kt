package com.gamerfinder.gamerfinder.dto

data class RoomDto(
    val description: String,
    val spots: Int,
    val mode: String,
    val ranks: List<String>
)
