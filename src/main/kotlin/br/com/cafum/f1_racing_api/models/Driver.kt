package br.com.cafum.f1_racing_api.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import lombok.Data
import java.util.*

@Data
@Entity(name = "drivers")
class Driver(
    @Id
    var id: UUID = UUID.randomUUID(),
    @Column(unique = true, nullable = false)
    var name: String = "",
    @Column
    var team: String? = null,
    @Column
    var number: Short? = null,
    @Column(nullable = false)
    var active: Boolean = true
)
