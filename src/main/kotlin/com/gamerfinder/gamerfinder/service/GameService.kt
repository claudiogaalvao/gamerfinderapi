package com.gamerfinder.gamerfinder.service

import com.gamerfinder.gamerfinder.model.Game
import com.gamerfinder.gamerfinder.model.Platforms
import org.springframework.stereotype.Service

@Service
class GameService(
    private var games: List<Game> = listOf()
) {

    fun getGames(): List<Game> {
        return games
    }

}
