package com.project2.myClimate.business

import com.project2.myClimate.dao.HomeRepository
import com.project2.myClimate.dao.UserRepository
import com.project2.myClimate.exception.BusinessException
import com.project2.myClimate.exception.NotFoundExceptionBusiness
import com.project2.myClimate.model.Home
import com.project2.myClimate.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserBusiness: IUserBusiness{

    @Autowired
    lateinit var userRepository: UserRepository //val userRepository: UserRepository? = null
    @Autowired
    lateinit var homeRepository: HomeRepository


    @Throws(BusinessException::class)
    override fun list(): List<User> {
        try {
            return userRepository.findAll()
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun listHomes(idUser: Long): List<Home> {
        try {
            val user = userRepository.findById(idUser)
            return homeRepository.findAllByOwner(owner = user.get())
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundExceptionBusiness::class)
    override fun load(idUser: Long): User {
        val opUser: Optional<User>
        try{
            opUser = userRepository.findById(idUser)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }

        if(!opUser.isPresent){
            throw NotFoundExceptionBusiness("User with id $idUser not found")
        }
        return opUser.get()
    }

    @Throws(BusinessException::class)
    override fun save(user: User): User {
        try{
            return userRepository.save(user)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundExceptionBusiness::class)
    override fun remove(idUser: Long) {
        val opUser: Optional<User>

        try {
            opUser = userRepository.findById(idUser)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opUser.isPresent){
            throw NotFoundExceptionBusiness("User with id $idUser not found")
        }else{
            try {
                userRepository.deleteById(idUser)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun authenticate(username: String, password: String): Boolean {
        val userFind = userRepository.findByUsername(username)
        val user = userRepository.findById(userFind?.id!!)
        return user.isPresent && user.get().password == password
    }

    override fun findUser(username: String): User {
        val user = userRepository.findByUsername(username)
        return user!!
    }

}