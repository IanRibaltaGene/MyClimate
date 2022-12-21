package com.project2.myClimate.business

import com.project2.myClimate.dao.HomeRepository
import com.project2.myClimate.exception.BusinessException
import com.project2.myClimate.exception.NotFoundExceptionBusiness
import com.project2.myClimate.exception.UserNotPermission
import com.project2.myClimate.model.Home
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class HomeBusiness: IHomeBusiness {

    @Autowired
    lateinit var homeRepository: HomeRepository
    @Autowired
    lateinit var userBusiness: UserBusiness

    @Throws(BusinessException::class)
    override fun listInfo(idHome: Long?): Any {
        try {
            return if(idHome != null){
                homeRepository.findById(idHome)
            }else{
                homeRepository.findAll()
            }
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun register(home: Home): Home {
        try {
            return homeRepository.save(home)
        }catch(e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundExceptionBusiness::class, UserNotPermission::class)
    override fun delete(idHome: Long, idUser: Long, password: String) {
        val opDeleted: Optional<Home>
        try {
            opDeleted = homeRepository.findById(idHome)
        }catch (e: BusinessException){
            throw BusinessException(e.message)
        }
        if(!opDeleted.isPresent){
            throw NotFoundExceptionBusiness("Home with id $idHome does not exist")
        }
        if(opDeleted.get().owner.id != idUser && opDeleted.get().owner.password != password){
            throw UserNotPermission("User $idUser is not the owner of this home")
        }
        try {
            homeRepository.deleteById(idHome)
        }catch(e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, UserNotPermission::class)
    override fun modify(home: Home): Home {
        val homeToModify = home.id?.let { homeRepository.findById(it) }

        if(!userBusiness.authenticate(home.owner) && home.owner != homeToModify?.get()?.owner){
            throw UserNotPermission("User ${home.owner} is not the owner of this home")
        }
        try {
            homeRepository.save(home)
            return home
        }catch(e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun searchHomeFullById(idHome: Long): Optional<Home> {
        try {
            return homeRepository.findById(idHome)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun searchHomeFullByAdd(address: String): Optional<Home> {
        try {
            return homeRepository.findHomeByAddress(address)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun searchHomeFullByDescription(description: String): Optional<Home> {
        try {
            return homeRepository.findAllByDescription(description)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun searchHomeFull(idHome: Long?, address: String?, description: String?): Optional<Home>{
        return if(idHome != null){
            println(idHome)
            searchHomeFullById(idHome)
        }else if(address != null){
            searchHomeFullByAdd(address)
        }else{
            searchHomeFullByDescription(description!!)
        }
    }


    override fun searchHomePartial(address: String, description: String): Home {
        TODO("Not yet implemented")
    }

}