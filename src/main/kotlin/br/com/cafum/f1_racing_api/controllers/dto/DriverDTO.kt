package br.com.cafum.f1_racing_api.controllers.dto

import lombok.Builder
import java.util.UUID

@Builder
data class DriverDTO(
    val id: UUID,
    val name: String,
    val number: Short,
    val team: String
)