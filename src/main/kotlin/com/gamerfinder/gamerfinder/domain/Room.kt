package com.gamerfinder.gamerfinder.domain

import com.gamerfinder.gamerfinder.dtos.output.RoomOutput
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.PrePersist
import java.time.LocalDateTime

@Entity
data class Room(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val gameId: Long,
    @OneToOne
    val playerHost: Player,
    @ElementCollection
    var playersIdJoined: List<Long> = emptyList(),
    var description: String,
    var spots: Int,
    var mode: String,
    @ElementCollection
    var ranks: List<String>,
    var createdAt: LocalDateTime? = null,
) {
    @PrePersist
    fun prePersist() {
        createdAt = LocalDateTime.now()
    }
}

fun Room.toOutput() = RoomOutput(
    id = id,
    playerHostName = playerHost.name,
    description = description,
    spots = spots,
    mode = mode,
    ranks = ranks,
)