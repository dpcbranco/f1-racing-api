package br.com.cafum.f1_racing_api.controllers

import br.com.cafum.f1_racing_api.controllers.dto.DTOMapper
import br.com.cafum.f1_racing_api.controllers.dto.DriverDTO
import br.com.cafum.f1_racing_api.services.driver.DriverService
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/drivers")
class DriversController(val driverService: DriverService) {

    @GetMapping
    fun getDrivers(@RequestParam active: Boolean): List<DriverDTO> {
        val driversList = driverService
            .listDrivers()
            .map { driver -> DTOMapper.toDriverDTO(driver) }
        return driversList;
    }

    @GetMapping("/{name}")
    fun getDriverById(@PathVariable name: String): ResponseEntity<DriverDTO>? {
        val driver = driverService.getDriverByName(name) ?:
            return ResponseEntity.notFound().build()
        return ResponseEntity.ok(DTOMapper.toDriverDTO(driver))
    }

    @PostMapping
    fun createDriver(@RequestBody driverDTO: DriverDTO): ResponseEntity<DriverDTO> {
        if (driverService.exists(driverDTO.name))
            return ResponseEntity.status(409).build()
        return ResponseEntity.ok(
            DTOMapper.toDriverDTO(driverService.createDriver(driverDTO))
        )
    }

    @PutMapping("/{name}")
    fun updateDriver(@PathVariable name: String, @RequestBody driverDTO: DriverDTO): ResponseEntity<DriverDTO> {
        if (!driverService.exists(driverDTO.name))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(
            DTOMapper.toDriverDTO(driverService.updateDriver(name, driverDTO))
        )
    }
}