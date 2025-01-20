package com.gamerfinder.gamerfinder.repository

import com.gamerfinder.gamerfinder.domain.Player

class PlayerRepository {

    fun getById(id: Long) = getPlayersMock().first { it.id == id }

}

private fun getPlayersMock() = listOf(
    Player(
        id = 100,
        name = "Jhon Doe",
        photoUrl = "https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png"
    ),
    Player(
        id = 101,
        name = "Jane Doe",
        photoUrl = "https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png"
    ),
    Player(
        id = 102,
        name = "Alice",
        photoUrl = "https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png"
    ),
    Player(
        id = 103,
        name = "Bob",
        photoUrl = "https://cdn.pixabay.com/photo"
    ),
    Player(
        id = 104,
        name = "Charlie",
        photoUrl = "https://cdn.pixabay.com/photo"
    )
)