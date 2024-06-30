package cz.schrek.tuzex.contracts.model.products

data class ProductSupplies(
    val productId: ProductId,
    val supplies: Int,
    val available: Int
)
