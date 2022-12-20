package com.project2.myClimate.dao

import com.project2.myClimate.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    @Query(
        value = "SELECT id FROM users u WHERE username = :username ORDER BY username ASC LIMIT 1",
        nativeQuery = true
    )
    fun findByUsername(@Param("username") username: String): User?
}