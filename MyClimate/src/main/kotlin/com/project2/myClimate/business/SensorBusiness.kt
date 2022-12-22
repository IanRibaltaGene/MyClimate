package com.project2.myClimate.business

import com.project2.myClimate.dao.HomeRepository
import com.project2.myClimate.dao.SensorRepository
import com.project2.myClimate.exception.BusinessException
import com.project2.myClimate.exception.NotFoundExceptionBusiness
import com.project2.myClimate.model.Sensor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SensorBusiness: ISensorBusiness {

    @Autowired
    lateinit var sensorRepository: SensorRepository
    @Autowired
    lateinit var homeRepository: HomeRepository

    @Throws(BusinessException::class, NotFoundExceptionBusiness::class)
    override fun list(idHome: Long): Optional<List<Sensor>> {
        try {
            val home = homeRepository.findById(idHome)
            if(!home.isPresent){
                throw NotFoundExceptionBusiness("Home does not exist")
            }
            return sensorRepository.findAllByHouse(home)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun save(sensor: Sensor): Sensor {
        try{
            return sensorRepository.save(sensor)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundExceptionBusiness::class)
    override fun delete(idSensor: Long) {
        val opSensor: Optional<Sensor>

        try {
            opSensor = sensorRepository.findById(idSensor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opSensor.isPresent){
            throw NotFoundExceptionBusiness("Sensor with id $idSensor not found")
        }else{
            try {
                sensorRepository.deleteById(idSensor)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class)
    override fun listAllSensors(): MutableList<Sensor> {
        try {
            return sensorRepository.findAll()
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    override fun listInfo(idSensor: Long): Any {
        TODO("Not yet implemented")
    }

}