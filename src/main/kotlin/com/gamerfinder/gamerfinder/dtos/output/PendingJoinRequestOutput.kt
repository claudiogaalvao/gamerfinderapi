package com.gamerfinder.gamerfinder.dtos.output

data class PendingJoinRequestOutput(
    val id: String,
    val roomId: String,
    val playerId: Int,
    val message: String? = null,
    val queuePosition: Int
)
