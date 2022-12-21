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
    lateinit var userRepository: UserRepository
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
            return homeRepository.findAllByOwner(owner = user)
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

    override fun authenticate(user: User): Boolean {
        val opUser = userRepository.findById(user.id!!)
        return opUser.isPresent && opUser.get().password == user.password
    }

    override fun findUser(username: String): User {
        val user = userRepository.findByUsername(username)
        return user!!
    }

}