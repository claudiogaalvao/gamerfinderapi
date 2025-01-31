package com.gamerfinder.gamerfinder.repository

import com.gamerfinder.gamerfinder.domain.Game
import com.gamerfinder.gamerfinder.domain.Platforms
import org.springframework.data.jpa.repository.JpaRepository

interface GameRepository: JpaRepository<Game, Long> {

    // fun getAll() = getGamesMock()

}
