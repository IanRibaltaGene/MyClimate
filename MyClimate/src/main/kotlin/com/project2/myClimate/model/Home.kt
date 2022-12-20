package com.project2.myClimate.model

import jakarta.persistence.*
import org.hibernate.Hibernate

@Entity
@Table(name = "home")
data class Home(var name: String = "",
                var address: String = "",
                var description: String = "",
                @ManyToOne var owner: User) {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    var id: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Home

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , address = $address , description = $description , owner = $owner )"
    }
}