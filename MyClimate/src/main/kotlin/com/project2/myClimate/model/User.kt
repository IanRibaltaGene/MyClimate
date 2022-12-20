package com.project2.myClimate.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(@Column(nullable = false) var username: String,
                @Column(nullable = false) var password: String,
                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                var id: Long? = null) {
    /*override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $username , password = $password )"
    }*/

}