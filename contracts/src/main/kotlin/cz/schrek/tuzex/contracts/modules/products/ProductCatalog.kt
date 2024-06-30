package cz.schrek.tuzex.contracts.modules.products

import cz.schrek.tuzex.contracts.model.products.Product

interface ProductCatalog {

    fun findProductById(id: String): Product?

    fun findProductsByName(name: String): List<Product>

    fun findProductsByCategory(category: String): List<Product>

    fun findProductsByPriceRange(from: Double?, to: Double?): List<Product>
}
