package com.project2.myClimate.business

import com.project2.myClimate.model.Sensor
import java.util.*

interface ISensorBusiness {
    fun list(idHome: Long): Optional<List<Sensor>>

    fun save(sensor: Sensor): Sensor

    fun delete(idSensor: Long)

    fun listAllSensors(): MutableList<Sensor>

    fun listInfo(idSensor: Long): Any

}