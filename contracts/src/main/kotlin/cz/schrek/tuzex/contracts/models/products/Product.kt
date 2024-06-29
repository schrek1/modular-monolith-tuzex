package cz.schrek.tuzex.contracts.models.products

import java.math.BigDecimal

data class Product(
    val id: ProductId,
    val name: String,
    val price: BigDecimal,
    val description: String,
    val category: String,
    val tags: List<String>,
)
