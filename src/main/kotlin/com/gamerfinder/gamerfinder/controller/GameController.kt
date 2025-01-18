package com.gamerfinder.gamerfinder.controller

import com.gamerfinder.gamerfinder.domain.Game
import com.gamerfinder.gamerfinder.service.GameService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/games")
class GameController(
    private val service: GameService
) {

    @GetMapping
    fun getGames(): List<Game> {
        return service.getGames()
    }

}