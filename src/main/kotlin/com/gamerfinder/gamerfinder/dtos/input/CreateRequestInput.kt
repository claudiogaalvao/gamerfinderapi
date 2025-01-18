package com.gamerfinder.gamerfinder.dtos.input

data class CreateRequestInput(
    val playerId: Int,
    val message: String? = null,
)