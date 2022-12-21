package com.project2.myClimate.web

import com.project2.myClimate.business.HomeBusiness
import com.project2.myClimate.exception.BusinessException
import com.project2.myClimate.model.Home
import com.project2.myClimate.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_HOMES)
class HomeRestController {

    @Autowired
    lateinit var homebusiness: HomeBusiness

    @PostMapping("")
    fun registerHome(@RequestBody home: Home): ResponseEntity<Any>{
        return try {
            homebusiness.register(home)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_HOMES + "/" + home.id)
            ResponseEntity(responseHeader, HttpStatus.CREATED)
        }catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}