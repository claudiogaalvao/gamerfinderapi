package com.gamerfinder.gamerfinder.controller

import com.gamerfinder.gamerfinder.dtos.output.GameSummaryOutput
import com.gamerfinder.gamerfinder.service.GameService
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/games")
class GameController(
    private val service: GameService
) {

    @GetMapping
    fun getGames(
        @RequestParam playerId: Long // TODO requires authentication
    ): List<GameSummaryOutput> {
        return service.getGames(playerId)
    }

    @PostMapping("/subscribe") // TODO requires authentication
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun subscribeToGame(
        @RequestParam gameId: Long,
        @RequestParam playerId: Long
    ) {
        service.subscribeToGame(gameId, playerId)
    }

    @DeleteMapping("/unsubscribe") // TODO requires authentication
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun unsubscribeToGame(
        @RequestParam gameId: Long,
        @RequestParam playerId: Long
    ) {
        service.unsubscribeToGame(gameId, playerId)
    }

}