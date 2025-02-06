package br.com.cafum.f1_racing_api.controllers

import br.com.cafum.f1_racing_api.controllers.dto.DriverDTO
import br.com.cafum.f1_racing_api.models.Driver
import br.com.cafum.f1_racing_api.services.driver.DriverService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatusCode
import java.util.*

class DriversControllerUTest {

    private val driverService = mockk<DriverService>()
    private val driversController = DriversController(driverService)

    private val mockDriver = Driver(
        id = UUID.randomUUID(),
        name = "Lando Norris",
        number = 4,
        team = "McLaren",
        active = true
    )
    private val updatedDriver = Driver(
        id = mockDriver.id,
        name = "Lando Norris",
        number = 5,
        team = "Mercedes",
        active = false
    )
    private val mockDriverDTO = DriverDTO(
        id = mockDriver.id,
        name = "Lando Norris",
        number = 4,
        team = "McLaren",
        active = true
    )
    private val updatedMockDriver = DriverDTO(
        id = mockDriver.id,
        name = "Lando Norris",
        number = 5,
        team = "Mercedes",
        active = false
    )


    @Test
    fun getDrivers() {
        every { driverService.listDrivers() } answers { listOf(mockDriver) }
        assertEquals(
            listOf(mockDriverDTO),
            driversController.getDrivers(true)
        )
    }

    @Test
    fun getDriverByName() {
        every { driverService.getDriverByName(mockDriver.name) } answers { mockDriver }
        assertEquals(
            mockDriverDTO,
            driversController.getDriverById(mockDriver.name)!!.body
        )
    }

    @Test
    fun getDriverByName_NotFound() {
        every { driverService.getDriverByName(mockDriver.name) } answers { null }

        val response = driversController.getDriverById(mockDriver.name)!!
        assertEquals(
            HttpStatusCode.valueOf(404),
            response.statusCode
        )
    }

    @Test
    fun createDriver() {
        every { driverService.exists(mockDriverDTO.name) } answers { false }
        every { driverService.createDriver(mockDriverDTO) } answers { mockDriver }

        val response = driversController.createDriver(mockDriverDTO)
        assertEquals(
            mockDriverDTO,
            response.body
        )
    }

    @Test
    fun createDriver_Conflict() {
        every { driverService.exists(mockDriverDTO.name) } answers { true }

        val response = driversController.createDriver(mockDriverDTO)
        assertEquals(
            HttpStatusCode.valueOf(409),
            response.statusCode
        )
    }

    @Test
    fun updatedDriver() {
        every { driverService.exists(mockDriverDTO.name) } answers { true }
        every { driverService.updateDriver(mockDriverDTO.name, updatedMockDriver) } answers { updatedDriver }

        val response = driversController.updateDriver(mockDriver.name, updatedMockDriver)
        assertEquals(
            updatedMockDriver,
            response.body
        )
    }

    @Test
    fun updateDriver_NotFound() {
        every { driverService.exists(mockDriverDTO.name) } answers { false }

        val response = driversController.updateDriver(mockDriver.name, mockDriverDTO)
        assertEquals(
            HttpStatusCode.valueOf(404),
            response.statusCode
        )
    }
}
