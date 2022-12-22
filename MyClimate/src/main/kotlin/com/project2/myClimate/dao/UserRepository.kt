package com.project2.myClimate.dao

import com.project2.myClimate.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findByUsername(@Param("username") username: String): User?
}