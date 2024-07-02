package cz.schrek.tuzex.contracts.model.common

import java.nio.CharBuffer

@JvmInline
value class Password(val value: CharArray) {

    companion object {
        fun from(value: String): Password = Password(value.toCharArray())
    }

    fun validateSecurityLevel() {
        require(value.size >= 8) { "Password must be at least 8 characters long." }
        require(value.any { it.isUpperCase() }) { "Password must contain at least one uppercase letter." }
        require(value.any { it.isLowerCase() }) { "Password must contain at least one lowercase letter." }
        require(value.any { it.isDigit() }) { "Password must contain at least one digit." }
    }

    fun clear() {
        value.forEachIndexed { idx, _ -> value[idx] = 0.toChar() }
    }

    fun toCharBuffer() = CharBuffer.wrap(value)

    override fun toString(): String = "Password(****)"
}
