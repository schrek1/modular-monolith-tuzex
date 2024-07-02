package cz.schrek.tuzex.customers.appliacation.service

import cz.schrek.tuzex.contracts.model.common.Password
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.security.SecureRandom


@Service
class PasswordHashService {

    private val bCrypt = BCryptPasswordEncoder(PASSWORD_STRENGTH, SecureRandom())

    fun hashPassword(password: Password): String = bCrypt.encode(password.toCharBuffer())

    companion object {
        private const val PASSWORD_STRENGTH = 16
    }
}
