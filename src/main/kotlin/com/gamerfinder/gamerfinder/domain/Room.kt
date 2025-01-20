package com.gamerfinder.gamerfinder.domain

import com.gamerfinder.gamerfinder.dtos.output.RoomOutput
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import java.time.LocalDateTime

@Entity
data class Room(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val gameId: Long,
    @OneToOne
    val playerHost: Player,
    val playersIdJoined: List<Long> = emptyList(),
    val description: String,
    val spots: Int,
    val mode: String,
    val ranks: List<String>,
    val createdAt: LocalDateTime,
)

fun Room.toOutput() = RoomOutput(
    id = id,
    playerHostName = playerHost.name,
    description = description,
    spots = spots,
    mode = mode,
    ranks = ranks,
)