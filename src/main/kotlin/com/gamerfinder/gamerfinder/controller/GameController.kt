package com.gamerfinder.gamerfinder.controller

import com.gamerfinder.gamerfinder.dtos.output.GameSummaryOutput
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
    fun getGames(): List<GameSummaryOutput> {
        return service.getGames()
    }

}