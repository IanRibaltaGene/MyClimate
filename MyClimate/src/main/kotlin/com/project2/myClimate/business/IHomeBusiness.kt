package com.project2.myClimate.business

import com.project2.myClimate.model.Home
import com.project2.myClimate.model.User

interface IHomeBusiness {

    fun listInfo(idHome: Long? = null): Home

    fun register(name: String, address: String, description: String, idOwner: Long): Home

    fun delete(idHome: Long, owner: User)

    fun modify(idHome: Long, name: String, address: String, description: String, owner: User): Home

    fun searchHomeFull(owner: User? = null, address: String, description: String): Home

    fun searchHomePartial(address: String, description: String): Home

}