package com.gamerfinder.gamerfinder.controller

import com.gamerfinder.gamerfinder.model.Room
import com.gamerfinder.gamerfinder.service.RoomService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rooms") // TODO: Receive the gameId as a parameter
class RoomController(
    private val service: RoomService
) {

    @GetMapping("/{gameId}")
    fun getRooms(@PathVariable gameId: Int): List<Room> {
        return service.getRooms(gameId)
    }

}