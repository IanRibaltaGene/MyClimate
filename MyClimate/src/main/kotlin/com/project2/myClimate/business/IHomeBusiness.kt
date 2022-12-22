package com.project2.myClimate.business

import com.project2.myClimate.model.Home
import com.project2.myClimate.model.User
import java.util.*

interface IHomeBusiness {

    fun listInfo(idHome: Long? = null): Any

    fun register(home: Home): Home

    fun delete(idHome: Long, idUser: Long, password: String)

    fun modify(home: Home, user: User): Home

    fun searchHomeFullById(idHome: Long): Optional<Home>

    fun searchHomeFullByAdd(address: String): Optional<Home>

    fun searchHomeFullByDescription(description: String): Optional<Home>

    fun searchHomePartial(address: String, description: String): Home

}