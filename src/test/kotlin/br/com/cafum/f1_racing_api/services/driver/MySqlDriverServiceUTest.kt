package br.com.cafum.f1_racing_api.services.driver

import br.com.cafum.f1_racing_api.controllers.dto.DriverDTO
import br.com.cafum.f1_racing_api.models.Driver
import br.com.cafum.f1_racing_api.repositories.DriverRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class MySqlDriverServiceUTest {

    private lateinit var driverRepository: DriverRepository
    private lateinit var driverService: MySqlDriverService

    @BeforeEach
    fun setUp() {
        driverRepository = mockk()
        driverService = MySqlDriverService(driverRepository)
    }

    @Test
    fun listDrivers() {
        val drivers = listOf(Driver(UUID.randomUUID(), "Driver 1", "Team A", 1, true))
        every { driverRepository.findAll() } returns drivers

        val result = driverService.listDrivers()
        assertEquals(drivers, result)
    }

    @Test
    fun getDriverByName() {
        val driver = Driver(UUID.randomUUID(), "Driver 1", "Team A", 1, true)
        every { driverRepository.findByName("Driver 1") } returns driver

        val result = driverService.getDriverByName("Driver 1")
        assertEquals(driver, result)
    }

    @Test
    fun getDriverByName_NotFound() {
        every { driverRepository.findByName("Unknown") } returns null

        val result = driverService.getDriverByName("Unknown")
        assertNull(result)
    }

    @Test
    fun exists_True() {
        every { driverRepository.existsByName("Driver 1") } returns true

        val result = driverService.exists("Driver 1")
        assertTrue(result)
    }

    @Test
    fun exists_False() {
        every { driverRepository.existsByName("Unknown") } returns false

        val result = driverService.exists("Unknown")
        assertFalse(result)
    }

    @Test
    fun createDriver() {
        val driverDTO = DriverDTO(id = UUID.randomUUID(), name="Driver 1", team = "Team A", number = 1, active = true)
        val driverSlot = slot<Driver>()
        every { driverRepository.save(capture(driverSlot)) } answers { driverSlot.captured }

        val result = driverService.createDriver(driverDTO)

        assertEquals(driverDTO.name, result.name)
        assertEquals(driverDTO.team, result.team)
        assertEquals(driverDTO.number, result.number)
        assertTrue(result.active)
        assertNotNull(result.id)
    }

    @Test
    fun updateDriver() {
        val existingDriver = Driver(UUID.randomUUID(), "Driver 1", "Team A", 1, true)
        val driverDTO = DriverDTO(id = existingDriver.id, name="Driver 1", team = "Team A", number = 1, active = true)
        every { driverRepository.findByName("Driver 1") } returns existingDriver
        every { driverRepository.save(any()) } answers { firstArg() }

        val result = driverService.updateDriver("Driver 1", driverDTO)

        assertEquals(driverDTO.team, result.team)
        assertEquals(driverDTO.number, result.number)
        assertEquals(driverDTO.active, result.active)
    }

    @Test
    fun updateDriver_NotFound() {
        every { driverRepository.findByName("Unknown") } returns null

        assertThrows<NullPointerException> {
            driverService.updateDriver(
                "Unknown",
                DriverDTO(id = UUID.randomUUID(), name = "Driver X", team = "Team Z", number = 77, active =true)
            )
        }
    }
}
