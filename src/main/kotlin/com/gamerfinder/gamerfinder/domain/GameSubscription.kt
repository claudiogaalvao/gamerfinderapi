package com.gamerfinder.gamerfinder.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.PrePersist
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@IdClass(SubscriptionId::class)
@Table(name = "subscriptions")
data class GameSubscription(
    @Id
    @Column(name = "game_id", nullable = false)
    val gameId: Long,

    @Id
    @Column(name = "player_id", nullable = false)
    val playerId: Long,

    @Column(name = "subscribed_at", nullable = false, updatable = false)
    var subscribedAt: LocalDateTime? = null
) {
    @PrePersist
    fun prePersist() {
        subscribedAt = LocalDateTime.now()
    }
}
