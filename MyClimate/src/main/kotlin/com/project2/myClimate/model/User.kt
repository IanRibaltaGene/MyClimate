package com.project2.myClimate.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(@Column(nullable = false) var username: String,
                @Column(nullable = false) var password: String,
                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                var id: Long = 0) {
}