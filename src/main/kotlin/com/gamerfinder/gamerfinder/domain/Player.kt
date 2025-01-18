package com.gamerfinder.gamerfinder.domain

data class Player(
    val id: Int,
    val name: String,
    val photoUrl: String? = null,
)