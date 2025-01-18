package com.gamerfinder.gamerfinder.exception

import java.time.LocalDateTime

data class ErrorResponse(
    val error: String,
    val message: String,
    val status: Int,
    val path: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
