package com.project2.myClimate.model

import jakarta.persistence.*

@Entity
@Table(name = "sensor")
class Sensor(var room: String = "",
             @ManyToOne var house: Home,
             @Id
             @GeneratedValue(strategy = GenerationType.AUTO)
             @Column(name = "id", nullable = false)
             var id: Long? = null) {

    /*override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Sensor

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , room = $room , house = $house )"
    }*/

}