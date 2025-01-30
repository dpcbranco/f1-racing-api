package br.com.cafum.f1_racing_api.services.driver

import br.com.cafum.f1_racing_api.controllers.dto.DriverDTO
import br.com.cafum.f1_racing_api.models.Driver

interface DriverService {

    fun listDrivers(): List<Driver>
    fun getDriverByName(name: String): Driver?
    fun exists(name: String): Boolean
    fun createDriver(driverDTO: DriverDTO): Driver
    fun updateDriver(name: String, driverDTO: DriverDTO): Driver
}