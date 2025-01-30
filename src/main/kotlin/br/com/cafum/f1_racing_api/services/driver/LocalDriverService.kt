package br.com.cafum.f1_racing_api.services.driver

import br.com.cafum.f1_racing_api.controllers.dto.DriverDTO
import br.com.cafum.f1_racing_api.models.Driver
import org.springframework.stereotype.Service
import java.util.UUID;

@Service("local-driver-service")
class LocalDriverService: DriverService {

    private val drivers: MutableMap<String, Driver> = mutableMapOf(
        Pair("Max Verstappen",
            Driver(
                name = "Max Verstappen",
                id = UUID.fromString("6d8b46b0-27c4-4334-8b8e-b9da233f23d5"),
                number = 1,
                team = "Red Bull Racing",
                active = true
            ))
    )

    override fun listDrivers(): List<Driver> {
        return drivers.values.toList();
    }

    override fun getDriverByName(name: String): Driver? {
        return drivers[name];
    }

    override fun exists(name: String): Boolean {
        return drivers.containsKey(name);
    }

    override fun createDriver(driverDTO: DriverDTO): Driver {
        val driver = Driver(
            id = UUID.randomUUID(),
            name = driverDTO.name,
            number = driverDTO.number,
            team = driverDTO.team,
            active = true
        )

        drivers[driverDTO.name] = driver;
        return driver
    }

    override fun updateDriver(name: String, driverDTO: DriverDTO): Driver {
        val driver = drivers[name]!!
        driver.team = driverDTO.team
        driver.number = driverDTO.number
        driver.active = false
        drivers[name] = driver;
        return driver;
    }
}