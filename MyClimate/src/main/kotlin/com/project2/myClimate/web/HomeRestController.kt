package com.project2.myClimate.web

import com.project2.myClimate.business.HomeBusiness
import com.project2.myClimate.exception.BusinessException
import com.project2.myClimate.exception.NotFoundExceptionBusiness
import com.project2.myClimate.exception.UserNotPermission
import com.project2.myClimate.model.Home
import com.project2.myClimate.utils.Constants
import com.project2.myClimate.utils.RequestWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(Constants.URL_BASE_HOMES)
class HomeRestController {

    @Autowired
    lateinit var homebusiness: HomeBusiness

    @GetMapping(value = arrayOf( "/list", "/list/{idHome}" ))
    fun list(@PathVariable("idHome",required = false)idHome: Long? = null): ResponseEntity<Any>{
        return try {
            ResponseEntity(homebusiness.listInfo(idHome), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/fullSearch")
    fun fullSearch(@RequestParam idHome: Long? = null,
                   @RequestParam address: String? = null,
                   @RequestParam description: String? = null): ResponseEntity<Optional<Home>>{
        return try {
            if(idHome != null){
                ResponseEntity(homebusiness.searchHomeFullById(idHome), HttpStatus.OK)
            }else if(address != null){
                ResponseEntity(homebusiness.searchHomeFullByAdd(address), HttpStatus.OK)
            }else if(description != null){
                ResponseEntity(homebusiness.searchHomeFullByDescription(description), HttpStatus.OK)
            }else{
                ResponseEntity(HttpStatus.BAD_REQUEST)
            }
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("")
    fun registerHome(@RequestBody home: Home): ResponseEntity<Any>{
        return try {
            val homeRegistered = homebusiness.register(home)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_HOMES + "/" + homeRegistered.id)
            ResponseEntity(responseHeader, HttpStatus.CREATED)
        }catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun updateHome(@RequestBody requestWrapper: RequestWrapper): ResponseEntity<Any>{
        return try {
            homebusiness.modify(requestWrapper.home, requestWrapper.user)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: UserNotPermission){
            ResponseEntity(HttpStatus.FORBIDDEN)
        }
    }

    @DeleteMapping("/delete/{idHome}/{idUser}/{password}")
    fun deleteHome(@PathVariable("idHome") idHome: Long,
                   @PathVariable("idUser") idUser: Long,
                   @PathVariable("password") password: String): ResponseEntity<Any>{
        return try {
            println(idHome)
            homebusiness.delete(idHome, idUser, password)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundExceptionBusiness){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }catch (e: UserNotPermission){
            ResponseEntity(HttpStatus.FORBIDDEN)
        }
    }
}