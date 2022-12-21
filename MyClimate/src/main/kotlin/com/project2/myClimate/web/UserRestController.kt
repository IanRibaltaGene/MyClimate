package com.project2.myClimate.web

import com.project2.myClimate.business.UserBusiness
import com.project2.myClimate.exception.BusinessException
import com.project2.myClimate.exception.NotFoundExceptionBusiness
import com.project2.myClimate.model.Home
import com.project2.myClimate.model.User
import com.project2.myClimate.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/{idUser}")
    fun load(@PathVariable("idUser") idUser: Long): ResponseEntity<User>{
        return try {
            ResponseEntity(userBusiness.load(idUser), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundExceptionBusiness){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun insert(@RequestBody user: User): ResponseEntity<Any>{
        return try {
            val userSaved = userBusiness.save(user)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_USERS + "/" + userSaved.id)
            ResponseEntity(HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody user: User): ResponseEntity<Any>{
        return try {
            userBusiness.save(user)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{idUser}")
    fun delete(@PathVariable("idUser") idUser: Long): ResponseEntity<Any>{
        return try {
            userBusiness.remove(idUser)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundExceptionBusiness){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}