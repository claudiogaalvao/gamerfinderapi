package com.gamerfinder.gamerfinder.dtos.response

class GameSummaryResponse(
    val id: Int,
    val name: String,
    val bannerUrl: String,
    val subscriptions: Int? = null,
    val minSubscriptions: Int? = null,
    val locked: Boolean? = null,
    val rooms: Int? = null,
    val displayOrder: Int,
)