package cz.schrek.tuzex.contracts.model.cart

data class Cart(
    val id: CartId,
    val alias: String?,
    val products: List<CartProduct>,
)
