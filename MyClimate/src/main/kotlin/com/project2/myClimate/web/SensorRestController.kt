package com.project2.myClimate.web

import com.project2.myClimate.business.SensorBusiness
import com.project2.myClimate.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.URL_BASE_SENSORS)
class SensorRestController {

    @Autowired
    lateinit var sensorBusiness: SensorBusiness

}