package com.gamerfinder.gamerfinder.dtos.response

data class UpdateRoomResponse(
    val description: String,
    val spots: Int,
    val mode: String,
    val ranks: List<String>,
)
