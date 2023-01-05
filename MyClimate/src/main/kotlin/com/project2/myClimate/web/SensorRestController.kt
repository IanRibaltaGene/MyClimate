package com.project2.myClimate.web

import com.project2.myClimate.business.SensorBusiness
import com.project2.myClimate.exception.BusinessException
import com.project2.myClimate.exception.NotFoundExceptionBusiness
import com.project2.myClimate.exception.UserNotPermission
import com.project2.myClimate.model.Sensor
import com.project2.myClimate.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(Constants.URL_BASE_SENSORS)
class SensorRestController {

    @Autowired
    lateinit var sensorBusiness: SensorBusiness

    @GetMapping("")
    fun listAll(): ResponseEntity<MutableList<Sensor>> {
        return try {
            ResponseEntity(sensorBusiness.listAllSensors(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{idHome}")
    fun listByHome(@PathVariable("idHome") idHome: Long): ResponseEntity<Optional<List<Sensor>>>{
        return try {
            ResponseEntity(sensorBusiness.list(idHome),HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundExceptionBusiness){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun registerSensor(@RequestBody sensor: Sensor): ResponseEntity<Any>{
        return try {
            val sensorRegistered = sensorBusiness.save(sensor)
            val responseHeader = HttpHeaders()
            responseHeader.set("sensor id", Constants.URL_BASE_HOMES + "/" + sensorRegistered.house.id)
            ResponseEntity(responseHeader, HttpStatus.CREATED)
        }catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun updateSensor(@RequestBody sensor: Sensor): ResponseEntity<Any>{
        return try {
            sensorBusiness.save(sensor)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: UserNotPermission){
            ResponseEntity(HttpStatus.FORBIDDEN)
        }
    }

    @DeleteMapping("/{idSensor}")
    fun delete(@PathVariable("idSensor") idSensor: Long): ResponseEntity<Any>{
        return try {
            sensorBusiness.delete(idSensor)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundExceptionBusiness){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}