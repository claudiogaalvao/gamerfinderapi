package com.gamerfinder.gamerfinder.domain

import com.gamerfinder.gamerfinder.domain.enums.JoinRequestStatus
import com.gamerfinder.gamerfinder.dtos.output.PendingJoinRequestOutput
import java.time.LocalDateTime

data class JoinRequest(
    val id: String,
    val roomId: String,
    val playerId: Int,
    val message: String? = null,
    val status: JoinRequestStatus,
    val queuePosition: Int,
    val createdAt: LocalDateTime
)

fun List<JoinRequest>.toOutput() = this.map {
    PendingJoinRequestOutput(
        id = it.id,
        roomId = it.roomId,
        playerId = it.playerId,
        message = it.message,
        queuePosition = it.queuePosition
    )
}