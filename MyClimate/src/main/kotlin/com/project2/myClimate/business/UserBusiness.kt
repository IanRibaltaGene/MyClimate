package com.project2.myClimate.business

import com.project2.myClimate.dao.HomeRepository
import com.project2.myClimate.dao.UserRepository
import com.project2.myClimate.exception.BusinessException
import com.project2.myClimate.model.Home
import com.project2.myClimate.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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
    override fun listHomes(user: User): List<Home> {
        try {
            return homeRepository.findAllByOwner(owner = user)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    override fun load(idUser: Long): User {
        TODO("Not yet implemented")
    }

    override fun save(user: User): User {
        TODO("Not yet implemented")
    }

    override fun remove(idUser: Long) {
        TODO("Not yet implemented")
    }

    override fun authenticate(username: String, password: String): Boolean {
        val idUser = userRepository.findByUsername(username)
        val user = userRepository.findById(idUser?.id!!)
        return user.isPresent && user.get().password == password
    }

}