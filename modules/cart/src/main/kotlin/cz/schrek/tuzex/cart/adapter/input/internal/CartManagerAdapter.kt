package cz.schrek.tuzex.cart.adapter.input.internal

import arrow.core.Either
import cz.schrek.tuzex.cart.application.service.CartService
import cz.schrek.tuzex.common.annotations.Adapter
import cz.schrek.tuzex.contracts.model.cart.Cart
import cz.schrek.tuzex.contracts.model.cart.CartId
import cz.schrek.tuzex.contracts.model.cart.CartProduct
import cz.schrek.tuzex.contracts.model.customers.CustomerId
import cz.schrek.tuzex.contracts.model.products.ProductId
import cz.schrek.tuzex.contracts.modules.cart.AddProductFailure
import cz.schrek.tuzex.contracts.modules.cart.CartManager
import cz.schrek.tuzex.contracts.modules.cart.SetProductQuantityFailure

@Adapter
class CartManagerAdapter(
    private val cartManager: CartService
) : CartManager {

    override fun createNewCart(customerId: CustomerId, alias: String?): CartId =
        cartManager.createNewCart(customerId.value, alias).let { CartId(it) }

    override fun addProduct(
        cartId: CartId,
        productId: ProductId,
        quantity: Int
    ): Either<AddProductFailure, Nothing> = cartManager.addProduct(productId.id, quantity)

    override fun setProductQuantity(
        cartId: CartId,
        productId: ProductId,
        quantity: Int
    ): Either<SetProductQuantityFailure, Nothing> = cartManager.setProductQuantity(productId.id, quantity)

    override fun removeProduct(cartId: CartId, productId: ProductId) {
        cartManager.removeProduct(productId.id)
    }

    override fun getCartsForCustomer(customerId: CustomerId): List<Cart> =
        cartManager.getCartsForCustomer(customerId.value).map {
            val cartEntries = cartManager.getCartEntries(it.cartId)
            Cart(
                id = CartId(it.cartId),
                alias = it.name,
                products = cartEntries.map {
                    CartProduct(
                        productId = ProductId(it.productId),
                        quantity = it.quantity
                    )
                }
            )
        }

    override fun clearCart(cartId: CartId) {
        cartManager.clearCart(cartId.id)
    }
}
