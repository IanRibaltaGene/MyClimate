package com.project2.myClimate.web

import com.project2.myClimate.business.UserBusiness
import com.project2.myClimate.model.Home
import com.project2.myClimate.model.User
import com.project2.myClimate.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.URL_BASE_USERS)
class UserRestController {

    @Autowired
    lateinit var userBusiness: UserBusiness

    @GetMapping("")
    fun list(): ResponseEntity<List<User>>{
        return try {
            ResponseEntity(userBusiness.list(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/homes{idUser}")
    fun listHomes(@PathVariable("idUser") idUser:Long): ResponseEntity<List<Home>>{
        return try {
            ResponseEntity(userBusiness.listHomes(idUser),HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}