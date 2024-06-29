package cz.schrek.tuzex.contracts.modules.cart

import arrow.core.Either
import cz.schrek.tuzex.contracts.models.cart.Cart
import cz.schrek.tuzex.contracts.models.cart.CartId
import cz.schrek.tuzex.contracts.models.customer.CustomerId
import cz.schrek.tuzex.contracts.models.products.ProductId

interface CartManager {
    fun createNewCart(customerId: CustomerId, alias: String? = null): CartId

    fun addProduct(
        cartId: CartId,
        productId: ProductId,
        quantity: Int
    ): Either<AddProductFailure, Nothing>

    fun setProductQuantity(
        cartId: CartId,
        productId: ProductId,
        quantity: Int
    ): Either<SetProductQuantityFailure, Nothing>

    fun removeProduct(cartId: CartId, productId: ProductId)

    fun getCartsForCustomer(customerId: CustomerId): List<Cart>

    fun clearCart(cartId: CartId)
}


sealed class AddProductFailure {
    data object CartNotFound : AddProductFailure()
    data object ProductAlreadyInCart : AddProductFailure()
}

sealed class SetProductQuantityFailure {
    data object CartNotFound : SetProductQuantityFailure()
    data object ProductNotInCart : SetProductQuantityFailure()
}
