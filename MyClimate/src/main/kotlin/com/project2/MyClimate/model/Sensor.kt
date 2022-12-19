package com.project2.MyClimate.model

import jakarta.persistence.*

@Entity
@Table(name = "sensor")
data class Sensor(@Id @GeneratedValue @Column(name = "id", nullable = false) var id: Long? = null,
                  var room: String,
                  @ManyToOne var house: Home) {

}