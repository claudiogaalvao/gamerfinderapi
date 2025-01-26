package com.gamerfinder.gamerfinder.repository

import com.gamerfinder.gamerfinder.domain.GameSubscription
import com.gamerfinder.gamerfinder.domain.SubscriptionId
import org.springframework.data.jpa.repository.JpaRepository

interface GameSubscriptionRepository: JpaRepository<GameSubscription, SubscriptionId> {
    fun findByPlayerId(playerId: Long): List<GameSubscription>
}