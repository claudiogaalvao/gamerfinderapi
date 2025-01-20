package com.gamerfinder.gamerfinder.domain

import com.gamerfinder.gamerfinder.domain.enums.JoinRequestStatus
import com.gamerfinder.gamerfinder.dtos.output.PendingJoinRequestOutput
import java.time.LocalDateTime

data class JoinRequest(
    val id: Long? = null,
    val roomId: Long,
    val playerId: Long,
    val message: String? = null,
    val status: JoinRequestStatus,
    val queuePosition: Int,
    val createdAt: LocalDateTime
)
