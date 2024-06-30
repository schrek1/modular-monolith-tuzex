package cz.schrek.tuzex.contracts.model.cart

import cz.schrek.tuzex.contracts.model.products.ProductId

data class CartProduct(
    val productId: ProductId,
    val quantity: Int,
)
