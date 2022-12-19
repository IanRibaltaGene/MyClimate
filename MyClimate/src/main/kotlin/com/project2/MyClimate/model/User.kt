package com.project2.MyClimate.model

import jakarta.persistence.*

@Entity
@Table(name = "user")
data class User(@Id @GeneratedValue @Column(name = "id", nullable = false) var id: Long,
                var name: String,
                var pass: String) {

}