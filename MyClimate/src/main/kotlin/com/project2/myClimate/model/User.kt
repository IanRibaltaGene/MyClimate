package com.project2.myClimate.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(@Column(nullable = false) var username: String,
                @Column(nullable = false) var password: String,
                @Id
                @GeneratedValue(strategy = GenerationType.AUTO)
           @Column(name = "id", nullable = false)
           var id: Long = 0) {
}