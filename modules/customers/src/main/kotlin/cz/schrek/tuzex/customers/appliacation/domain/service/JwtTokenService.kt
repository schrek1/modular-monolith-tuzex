package cz.schrek.tuzex.customers.appliacation.domain.service

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service

@Service
class JwtTokenService {

    fun generateToken(){
        Jwts.builder()

    }
}
