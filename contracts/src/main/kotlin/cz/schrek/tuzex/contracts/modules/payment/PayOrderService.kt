package cz.schrek.tuzex.contracts.modules.payment

import cz.schrek.tuzex.contracts.model.orders.OrderId

interface PayOrderService {
    fun payOrder(orderId: OrderId)
}
