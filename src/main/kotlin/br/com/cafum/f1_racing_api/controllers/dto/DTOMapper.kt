package br.com.cafum.f1_racing_api.controllers.dto

import br.com.cafum.f1_racing_api.models.Driver

class DTOMapper {
    companion object {
        fun toDriverDTO(driver: Driver): DriverDTO {
            return DriverDTO(
                id = driver.id,
                name = driver.name,
                number = driver.number,
                team = driver.team,
                active = driver.active
            )
        }
    }
}