package com.project2.myClimate.dao

import com.project2.myClimate.model.Home
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HomeRepository: JpaRepository<Home, Long> {
}