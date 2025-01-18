package com.gamerfinder.gamerfinder.dtos.response

data class RoomResponse(
    val id: String,
    val playerHostName: String,
    val description: String,
    val spots: Int,
    val mode: String,
    val ranks: List<String>,
)
