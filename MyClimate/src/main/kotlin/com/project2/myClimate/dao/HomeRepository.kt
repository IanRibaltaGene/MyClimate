package com.project2.myClimate.dao

import com.project2.myClimate.model.Home
import com.project2.myClimate.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HomeRepository: JpaRepository<Home, Long> {

    fun findAllByOwner(@Param("owner") owner: Optional<User>): List<Home>


    fun findAllByDescription(@Param("description")description: String): Optional<Home>


    fun findHomeByAddress(@Param("address")address: String): Optional<Home>
}