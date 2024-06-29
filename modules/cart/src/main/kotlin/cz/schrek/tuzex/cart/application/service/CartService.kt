package cz.schrek.tuzex.cart.application.service

import arrow.core.Either
import cz.schrek.tuzex.cart.application.model.CartEntry
import cz.schrek.tuzex.cart.application.model.UserCart
import cz.schrek.tuzex.contracts.modules.cart.AddProductFailure
import cz.schrek.tuzex.contracts.modules.cart.SetProductQuantityFailure
import org.springframework.stereotype.Service
import java.util.*

@Service
class CartService {
    fun createNewCart(id: UUID, alias: String?): UUID {
        TODO("Not yet implemented")
    }

    fun addProduct(productId: UUID, quantity: Int): Either<AddProductFailure, Nothing> {
        TODO("Not yet implemented")
    }

    fun setProductQuantity(productId: UUID, quantity: Int): Either<SetProductQuantityFailure, Nothing> {
        TODO("Not yet implemented")
    }

    fun removeProduct(productId: UUID) {
        TODO("Not yet implemented")
    }

    fun getCartEntries(cartId: UUID): List<CartEntry> {
        TODO("Not yet implemented")
    }

    fun clearCart(id: UUID) {
        TODO("Not yet implemented")
    }

    fun getCartsForCustomer(customerId: UUID): List<UserCart> {
        TODO("Not yet implemented")
    }
}
