package br.com.cafum.f1_racing_api.controllers.dto

import java.util.UUID

data class DriverDTO(
    val id: UUID?,
    val name: String,
    val number: Short,
    val team: String,
    val active: Boolean?
)