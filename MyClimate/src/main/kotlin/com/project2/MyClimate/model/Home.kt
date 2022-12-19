package com.project2.MyClimate.model

import jakarta.persistence.*

@Entity
@Table(name = "home")
data class Home(@Id @GeneratedValue @Column(name = "id", nullable = false) var id: Long? = null,
                var name: String,
                var address: String,
                var description: String,
                @ManyToOne var owner: User) {

}