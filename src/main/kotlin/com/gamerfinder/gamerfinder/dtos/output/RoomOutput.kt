package com.gamerfinder.gamerfinder.dtos.output

data class RoomOutput(
    val id: String,
    val playerHostName: String,
    val description: String,
    val spots: Int,
    val mode: String,
    val ranks: List<String>,
)
