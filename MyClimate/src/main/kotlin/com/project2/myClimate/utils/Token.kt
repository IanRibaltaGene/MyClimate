package com.project2.myClimate.utils


import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.crypto.KeyGenerator

@Component
class Token(@Value("\${jwt.expiration:3600}") val expiration: Long
) {
    fun generateToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>()
        val keyGenerator = KeyGenerator.getInstance("AES")
        keyGenerator.init(256)
        val secret : Key
        secret = keyGenerator.generateKey()
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration * 1000))
            .signWith(secret, SignatureAlgorithm.HS512)
            .compact()
    }
}