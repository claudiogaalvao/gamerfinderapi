package com.gamerfinder.gamerfinder.dtos.input

data class CreateJoinRequestInput(
    val playerId: Int,
    val message: String? = null,
)