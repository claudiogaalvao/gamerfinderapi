package com.gamerfinder.gamerfinder.service

import com.gamerfinder.gamerfinder.domain.toSummaryResponse
import com.gamerfinder.gamerfinder.dtos.output.GameSummaryOutput
import com.gamerfinder.gamerfinder.repository.GameRepository
import org.springframework.stereotype.Service

@Service
class GameService(
    private val gameRepository: GameRepository
) {

    fun getGames(): List<GameSummaryOutput> {
        return gameRepository.findAll().toSummaryResponse()
    }

}
