package br.com.cafum.f1_racing_api.repositories

import br.com.cafum.f1_racing_api.models.Driver
import org.springframework.data.jpa.repository.JpaRepository

interface DriverRepository: JpaRepository<Driver, String> {
    fun findByName(name: String): Driver?
    fun existsByName(name: String): Boolean
}