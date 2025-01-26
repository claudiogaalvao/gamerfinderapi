package com.gamerfinder.gamerfinder.dtos.output

class GameSummaryOutput(
    val id: Long,
    val name: String,
    val bannerUrl: String,
    val subscribed: Boolean? = null,
    val subscriptions: Int? = null,
    val minSubscriptions: Int? = null,
    val locked: Boolean? = null,
    val rooms: Int? = null,
    val displayOrder: Int,
)