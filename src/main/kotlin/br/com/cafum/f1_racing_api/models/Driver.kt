package br.com.cafum.f1_racing_api.models

import java.util.UUID

data class Driver(
    val id: UUID,
    val name: String,
    var team: String,
    var number: Short,
    var active: Boolean
)
