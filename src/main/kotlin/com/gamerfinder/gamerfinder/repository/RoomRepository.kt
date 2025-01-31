package com.gamerfinder.gamerfinder.repository

import com.gamerfinder.gamerfinder.domain.Room
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface RoomRepository: JpaRepository<Room, Long> {

    fun findByGameIdAndCreatedAtAfter(id: Long, date: LocalDateTime, pagination: Pageable): Page<Room>

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Room r " +
            "WHERE r.id = :roomId AND r.playerHost.id = :playerId")
    fun isPlayerHostOfRoom(roomId: Long, playerId: Long): Boolean

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Room r " +
            "WHERE r.id = :roomId AND :playerId MEMBER OF r.playersIdJoined")
    fun existsPlayerInRoom(roomId: Long, playerId: Long): Boolean

}
