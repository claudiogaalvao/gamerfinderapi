package com.gamerfinder.gamerfinder.controller

import com.gamerfinder.gamerfinder.dto.RoomDto
import com.gamerfinder.gamerfinder.model.Room
import com.gamerfinder.gamerfinder.service.RoomService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rooms")
class RoomController(
    private val service: RoomService
) {

    @GetMapping("/{gameId}")
    fun getRooms(@PathVariable gameId: Int): List<Room> {
        return service.getRooms(gameId)
    }

    @PostMapping
    fun createRoom(
        @RequestParam gameId: Int,
        @RequestParam playerId: Int,
        @RequestBody room: RoomDto
    ) {
        service.createRoom(
            gameId = gameId,
            playerId = playerId,
            room = room
        )
    }

}