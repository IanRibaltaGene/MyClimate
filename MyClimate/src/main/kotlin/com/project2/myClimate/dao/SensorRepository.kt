package com.project2.myClimate.dao

import com.project2.myClimate.model.Home
import com.project2.myClimate.model.Sensor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SensorRepository: JpaRepository<Sensor, Long> {

    fun findAllByHouse(home: Optional<Home>): Optional<List<Sensor>>
}