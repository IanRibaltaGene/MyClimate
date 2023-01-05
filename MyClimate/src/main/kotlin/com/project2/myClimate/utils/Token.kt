package com.project2.myClimate.utils


import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class Token(@Value ("\${jwt.secret}") val secret: String,
    @Value("\${jwt.expiration}") val expiration: Long
) {
    fun generateToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>()
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration * 1000))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
    }
}