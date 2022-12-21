package com.project2.myClimate.business

import com.project2.myClimate.dao.HomeRepository
import com.project2.myClimate.dao.UserRepository
import com.project2.myClimate.exception.BusinessException
import com.project2.myClimate.exception.NotFoundExceptionBusiness
import com.project2.myClimate.exception.UserNotPermission
import com.project2.myClimate.model.Home
import com.project2.myClimate.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class HomeBusiness: IHomeBusiness {

    @Autowired
    lateinit var homeRepository: HomeRepository
    @Autowired
    lateinit var userRepository: UserRepository

    @Throws(BusinessException::class)
    override fun listInfo(idHome: Long?): Any {
        try {
            if(idHome != null){
                return homeRepository.findById(idHome)
            }
            return homeRepository.findAll()
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

    override fun delete(idHome: Long, owner: User) {
        val opDeleted: Optional<Home>
        try {
            opDeleted = homeRepository.findById(idHome)
        }catch (e: BusinessException){
            throw BusinessException(e.message)
        }
        if(!opDeleted.isPresent){
            throw NotFoundExceptionBusiness("Home with id $idHome does not exist")
        }
        if(opDeleted.get().owner != owner){
            throw UserNotPermission("User $owner is not the owner of this home")
        }
        try {
            homeRepository.deleteById(idHome)
        }catch(e: Exception){
            throw BusinessException(e.message)
        }
    }

    override fun modify(idHome: Long, name: String, address: String, description: String, owner: User): Home {
        val home = Home(name, address, description, owner, idHome)
        val homeToModify = homeRepository.findById(idHome)
        if(homeToModify.get().owner != owner){
            throw UserNotPermission("User $owner is not the owner of this home")
        }
        try {
            homeRepository.save(home)
            return home
        }catch(e: Exception){
            throw BusinessException(e.message)
        }
    }

    override fun searchHomeFullById(idHome: Long): Optional<Home> {
        try {
            return homeRepository.findById(idHome)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    override fun searchHomeFullByAdd(address: String): Optional<Home> {
        try {
            return homeRepository.findHomeByAdd(address)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    override fun searchHomeFullByDescription(description: String): Any {
        try {
            return homeRepository.findAllByDescription(description)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }


    override fun searchHomePartial(address: String, description: String): Home {
        TODO("Not yet implemented")
    }

}