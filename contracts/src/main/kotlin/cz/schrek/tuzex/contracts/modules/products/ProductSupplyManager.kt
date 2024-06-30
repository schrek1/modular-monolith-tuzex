package cz.schrek.tuzex.contracts.modules.products

import arrow.core.Either
import cz.schrek.tuzex.contracts.model.orders.OrderId
import cz.schrek.tuzex.contracts.model.products.ProductId
import cz.schrek.tuzex.contracts.model.products.ProductSupplies

interface ProductSupplyManager {
    fun supplyProduct(
        productId: ProductId,
        orderId: OrderId,
        quantity: Int
    ): Either<ProductSupplyFailure, ProductSupplies>

    fun reserveProduct(
        productId: ProductId,
        orderId: OrderId,
        quantity: Int
    ): Either<ProductReservationFailure, ProductSupplies>

    fun releaseProductReservation(
        productId: ProductId,
        orderId: OrderId,
        quantity: Int
    ): Either<ProductReservationReleaseFailure, ProductSupplies>

    fun stockOutProduct(
        productId: ProductId,
        orderId: OrderId,
        quantity: Int
    ): Either<StockOutFailure, ProductSupplies>

    fun getProductSupply(productId: ProductId): ProductSupplies?
}

sealed class ProductSupplyFailure {
    data object ProductNotFound : ProductSupplyFailure()
}

sealed class ProductReservationFailure {
    data object ProductNotFound : ProductReservationFailure()
    data object NotEnoughSupplies : ProductReservationFailure()
}

sealed class ProductReservationReleaseFailure {
    data object ProductNotFound : ProductReservationReleaseFailure()
    data object NotEnoughReservations : ProductReservationReleaseFailure()
}


sealed class StockOutFailure {
    data object ProductNotFound : StockOutFailure()
    data object NotEnoughSupplies : StockOutFailure()
}
