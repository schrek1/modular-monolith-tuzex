package cz.schrek.tuzex.cart.application.model

import java.time.Instant
import java.util.*

data class UserCart(
    val userId: UUID,
    val cartId: UUID,
    val name: String,
    val entries: Set<CartEntry>,
    val createdAt: Instant,
)

data class CartEntry(
    val productId: UUID,
    val quantity: Int,
    val insertedAt: Instant,
    val updatedAt: Instant
)
