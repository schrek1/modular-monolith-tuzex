package cz.schrek.tuzex.contracts.models.cart

data class Cart(
    val id: CartId,
    val alias: String?,
    val products: List<CartProduct>,
)
