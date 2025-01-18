package com.gamerfinder.gamerfinder.service

import com.gamerfinder.gamerfinder.domain.Game
import org.springframework.stereotype.Service

@Service
class GameService(
    private var games: List<Game> = listOf()
) {

    fun getGames(): List<Game> {
        return games
    }

}
