package com.project2.myClimate.dao

import com.project2.myClimate.model.Home
import com.project2.myClimate.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HomeRepository: JpaRepository<Home, Long> {
    @Query(
        value = "SELECT name FROM home h WHERE owner = :owner",
        nativeQuery = true
    )
    fun findAllByOwner(@Param("owner")owner: User): List<Home>

    @Query(
        value = "SELECT * FROM home h WHERE description = :description",
        nativeQuery = true
    )
    fun findAllByDescription(@Param("description")description: String): List<Home>

    @Query(
        value = "SELECT * FROM home h WHERE address = :address",
        nativeQuery = true
    )
    fun findHomeByAdd(@Param("address")address: String): Optional<Home>
}