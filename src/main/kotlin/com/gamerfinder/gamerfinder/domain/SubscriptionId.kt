package com.gamerfinder.gamerfinder.domain

import java.io.Serializable

data class SubscriptionId(
    val gameId: Long = 0L,
    val playerId: Long = 0L
) : Serializable
