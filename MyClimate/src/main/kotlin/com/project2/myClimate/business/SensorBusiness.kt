package com.project2.myClimate.business

import com.project2.myClimate.dao.SensorRepository
import com.project2.myClimate.model.Home
import com.project2.myClimate.model.Sensor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SensorBusiness: ISensorBusiness {

    @Autowired
    lateinit var sensorRepository: SensorRepository
    override fun list(idHome: Long): List<Sensor> {
        TODO("Not yet implemented")
    }

    override fun register(idSensor: Long, home: Home, room: String): Sensor {
        TODO("Not yet implemented")
    }

    override fun delete(idSensor: Long) {
        TODO("Not yet implemented")
    }

    override fun listInfo(idSensor: Long): Map<String, Any> {
        TODO("Not yet implemented")
    }

}