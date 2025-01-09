package br.com.cafum.f1_racing_api.controllers

import br.com.cafum.f1_racing_api.controllers.dto.DriverDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/drivers")
class DriversController {

    @GetMapping
    fun getDrivers(@RequestParam active: Boolean): ResponseEntity<List<DriverDTO>> {
        val driverDTO = DriverDTO(
            name = "Max Verstappen",
            id = UUID.randomUUID(),
            number = 1,
            team = "Red Bull Racing"
        )
        return ResponseEntity.ok(listOf(driverDTO))
    }
}