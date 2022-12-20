package com.project2.myClimate.business

import com.project2.myClimate.model.Home
import com.project2.myClimate.model.Sensor

interface ISensorBusiness {
    fun list(idHome: Long): List<Sensor>

    fun register(idSensor: Long, home: Home, room: String): Sensor

    fun delete(idSensor: Long)

    fun listInfo(idSensor: Long): Map<String, Any>

}