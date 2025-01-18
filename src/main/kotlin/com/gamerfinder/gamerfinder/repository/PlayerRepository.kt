package com.gamerfinder.gamerfinder.repository

import com.gamerfinder.gamerfinder.domain.Player

class PlayerRepository {

    fun getById(id: Int) = getPlayersMock().first { it.id == id }

}

private fun getPlayersMock() = listOf(
    Player(
        id = 414,
        name = "Jhon Doe",
        photoUrl = "https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png"
    ),
    Player(
        id = 3213,
        name = "Jane Doe",
        photoUrl = "https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png"
    ),
    Player(
        id = 23123,
        name = "Alice",
        photoUrl = "https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png"
    )
)