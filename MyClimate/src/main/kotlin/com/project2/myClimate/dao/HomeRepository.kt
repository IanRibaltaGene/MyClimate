package com.project2.myClimate.dao

import com.project2.myClimate.model.Home
import com.project2.myClimate.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface HomeRepository: JpaRepository<Home, Long> {
    //@Query("SELECT name FROM home h WHERE h.owner = :owner")
    @Query(
        value = "SELECT name FROM home h WHERE owner = :owner",
        nativeQuery = true
    )
    fun findAllByOwner(@Param("owner")owner: User): List<Home>
}