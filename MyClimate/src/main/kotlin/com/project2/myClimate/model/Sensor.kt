package com.project2.myClimate.model

import jakarta.persistence.*

@Entity
@Table(name = "sensor")
class Sensor(var room: String = "",
             @ManyToOne var house: Home,
             @Id
             @GeneratedValue(strategy = GenerationType.AUTO)
             @Column(name = "id", nullable = false)
             var id: Long = 0) {
}