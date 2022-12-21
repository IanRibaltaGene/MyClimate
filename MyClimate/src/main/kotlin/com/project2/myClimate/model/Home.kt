package com.project2.myClimate.model

import jakarta.persistence.*

@Entity
@Table(name = "home")
class Home(var name: String = "",
           @Column(unique = true) var address: String = "",
           var description: String = "",
           @ManyToOne var owner: User,
           @Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           @Column(name = "id", nullable = false)
           var id: Long? = null) {
}