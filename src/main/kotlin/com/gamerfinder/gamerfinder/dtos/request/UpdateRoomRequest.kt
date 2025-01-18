package com.gamerfinder.gamerfinder.dtos.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UpdateRoomRequest(
    @field:NotEmpty
    @field:Size(min = 20, max = 200)
    val description: String,
    @field:NotNull
    val spots: Int,
    @field:NotEmpty
    val mode: String,
    val ranks: List<String>
)
