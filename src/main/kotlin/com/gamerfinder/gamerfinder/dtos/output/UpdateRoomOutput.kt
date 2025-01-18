package com.gamerfinder.gamerfinder.dtos.output

data class UpdateRoomOutput(
    val description: String,
    val spots: Int,
    val mode: String,
    val ranks: List<String>,
)
