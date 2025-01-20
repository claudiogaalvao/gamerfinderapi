package com.gamerfinder.gamerfinder.dtos.input

data class CreateJoinRequestInput(
    val playerId: Long,
    val message: String? = null,
)