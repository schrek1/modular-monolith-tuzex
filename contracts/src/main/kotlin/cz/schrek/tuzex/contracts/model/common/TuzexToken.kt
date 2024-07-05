package cz.schrek.tuzex.contracts.model.common

import java.time.Instant

class TuzexToken(
    val token: CharArray,
    val expiresAt: Instant? = null
) {
    companion object {
        fun from(token: String, expiresAt: Instant? = null) = TuzexToken(
            token = token.toCharArray(),
            expiresAt = expiresAt
        )
    }

    fun clear() {
        token.forEachIndexed { idx, _ -> token[idx] = 0.toChar() }
    }

    override fun toString(): String = "Token(token=${token.take(4)}***${token.takeLast(4)}, expiresAt=$expiresAt"
}
