package com.project2.myClimate.business

import com.project2.myClimate.model.User

interface IUserBusiness {

    fun list(): List<User>
}