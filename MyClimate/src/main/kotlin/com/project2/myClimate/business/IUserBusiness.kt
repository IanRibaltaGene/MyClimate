package com.project2.myClimate.business

import com.project2.myClimate.model.Home
import com.project2.myClimate.model.User

interface IUserBusiness {

    fun list(): List<User>
    fun listHomes(user: User): List<Home>
    fun load(idUser: Long): User
    fun save(user: User): User
    fun remove(idUser: Long)
    fun authenticate(username: String, password: String): Boolean

}