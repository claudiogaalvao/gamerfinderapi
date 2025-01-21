package com.gamerfinder.gamerfinder.repository

import com.gamerfinder.gamerfinder.domain.Room
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface RoomRepository: JpaRepository<Room, Long> {

    fun findByGameIdAndCreatedAtAfter(id: Long, date: LocalDateTime): List<Room>

    fun getRoomsByGameId(gameId: Long): List<Room>

}
