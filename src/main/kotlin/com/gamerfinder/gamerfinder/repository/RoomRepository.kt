package com.gamerfinder.gamerfinder.repository

import com.gamerfinder.gamerfinder.domain.Room
import org.springframework.data.jpa.repository.JpaRepository

interface RoomRepository: JpaRepository<Room, Long> {

}
