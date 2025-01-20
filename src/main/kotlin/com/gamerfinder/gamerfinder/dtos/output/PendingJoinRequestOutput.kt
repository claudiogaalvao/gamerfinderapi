package com.gamerfinder.gamerfinder.dtos.output

data class PendingJoinRequestOutput(
    val id: Long,
    val roomId: Long,
    val playerId: Long,
    val message: String? = null,
    val queuePosition: Int
)
