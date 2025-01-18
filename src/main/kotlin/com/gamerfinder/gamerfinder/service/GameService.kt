package com.gamerfinder.gamerfinder.service

import com.gamerfinder.gamerfinder.domain.toSummaryResponse
import com.gamerfinder.gamerfinder.dtos.response.GameSummaryResponse
import com.gamerfinder.gamerfinder.repository.GameRepository
import org.springframework.stereotype.Service

@Service
class GameService(
    private val gameRepository: GameRepository = GameRepository()
) {

    fun getGames(): List<GameSummaryResponse> {
        return gameRepository.getAll().toSummaryResponse()
    }

}
