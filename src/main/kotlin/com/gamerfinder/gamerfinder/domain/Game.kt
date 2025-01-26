package com.gamerfinder.gamerfinder.domain

import com.gamerfinder.gamerfinder.dtos.output.GameSummaryOutput
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Game(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val bannerUrl: String,
    val logoUrl: String,
    var subscriptions: Int,
    val minSubscriptions: Int,
    val rooms: Int? = null,
    @ElementCollection
    val modes: List<String>? = null,
    @ElementCollection
    val ranks: List<String>? = null,
    @ElementCollection
    val positions: List<String>? = null,
    @ElementCollection
    @Enumerated(EnumType.STRING)
    val platforms: List<Platforms>? = null,
)

fun Game.toSummaryResponse(index: Int, subscribed: Boolean): GameSummaryOutput {
    return GameSummaryOutput(
        id = this.id,
        name = this.name,
        bannerUrl = this.bannerUrl,
        subscribed = subscribed,
        subscriptions = this.subscriptions,
        minSubscriptions = this.minSubscriptions,
        locked = this.subscriptions <= this.minSubscriptions,
        rooms = this.rooms,
        displayOrder = index,
    )
}
