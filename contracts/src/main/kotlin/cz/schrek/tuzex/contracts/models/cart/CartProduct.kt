package cz.schrek.tuzex.contracts.models.cart

import cz.schrek.tuzex.contracts.models.products.ProductId

data class CartProduct(
    val productId: ProductId,
    val quantity: Int,
)
