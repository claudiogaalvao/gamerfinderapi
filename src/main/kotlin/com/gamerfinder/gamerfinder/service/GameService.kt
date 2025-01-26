package com.gamerfinder.gamerfinder.service

import com.gamerfinder.gamerfinder.domain.GameSubscription
import com.gamerfinder.gamerfinder.domain.SubscriptionId
import com.gamerfinder.gamerfinder.domain.toSummaryResponse
import com.gamerfinder.gamerfinder.dtos.output.GameSummaryOutput
import com.gamerfinder.gamerfinder.repository.GameRepository
import com.gamerfinder.gamerfinder.repository.GameSubscriptionRepository
import org.springframework.stereotype.Service

@Service
class GameService(
    private val gameRepository: GameRepository,
    private val gameSubscriptionRepository: GameSubscriptionRepository
) {

    fun getGames(playerId: Long): List<GameSummaryOutput> {
        val subscriptions = gameSubscriptionRepository.findByPlayerId(playerId)
        return gameRepository.findAll().mapIndexed { index, game ->
            game.toSummaryResponse(
                index = index,
                subscribed = subscriptions.any { it.gameId == game.id }
            )
        }
    }

    fun subscribeToGame(gameId: Long, playerId: Long) {
        if (gameSubscriptionRepository.existsById(SubscriptionId(gameId, playerId))) {
            throw(IllegalArgumentException("Already subscribed"))
        }
        val game = gameRepository.findById(gameId).orElseThrow()
        game.subscriptions = game.subscriptions.plus(1)
        val gameSubscription = GameSubscription(
            gameId = gameId,
            playerId = playerId
        )
        gameSubscriptionRepository.save(gameSubscription)
    }

    fun unsubscribeToGame(gameId: Long, playerId: Long) {
        val subscriptionId = SubscriptionId(gameId, playerId)
        if (gameSubscriptionRepository.existsById(subscriptionId).not()) {
            throw(IllegalArgumentException("Subscription not found"))
        }
        val game = gameRepository.findById(gameId).orElseThrow()
        game.subscriptions = game.subscriptions.minus(1)
        gameSubscriptionRepository.deleteById(subscriptionId)
    }

}
