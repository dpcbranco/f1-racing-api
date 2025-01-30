package br.com.cafum.f1_racing_api.services.driver

import br.com.cafum.f1_racing_api.controllers.dto.DriverDTO
import br.com.cafum.f1_racing_api.models.Driver
import br.com.cafum.f1_racing_api.repositories.DriverRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service("mysql-driver-service")
class MySqlDriverService (val driverRepository: DriverRepository): DriverService{
    override fun listDrivers(): List<Driver> {
        return driverRepository.findAll()
    }

    override fun getDriverByName(name: String): Driver? {
        return driverRepository.findByName(name)
    }

    override fun exists(name: String): Boolean {
        return driverRepository.existsByName(name)
    }

    override fun createDriver(driverDTO: DriverDTO): Driver {
        val driver = Driver(
            name = driverDTO.name,
            team = driverDTO.team,
            number = driverDTO.number,
            id = UUID.randomUUID(),
            active = true
        )
        return driverRepository.save(driver)
    }

    override fun updateDriver(name: String, driverDTO: DriverDTO): Driver {
        val driver = driverRepository.findByName(name)!!
        driver.team = driverDTO.team
        driver.number = driverDTO.number
        driver.active = driverDTO.active ?: true
        return driverRepository.save(driver)
    }

}