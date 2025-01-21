package com.gamerfinder.gamerfinder.repository

import com.gamerfinder.gamerfinder.domain.Player
import org.springframework.data.jpa.repository.JpaRepository

interface PlayerRepository: JpaRepository<Player, Long>