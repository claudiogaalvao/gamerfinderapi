package com.gamerfinder.gamerfinder.model

data class Player(
    val id: Int,
    val name: String,
    val photoUrl: String? = null,
)