package cz.schrek.tuzex.contracts.modules.customers

import cz.schrek.tuzex.contracts.models.customer.Customer
import cz.schrek.tuzex.contracts.models.customer.CustomerId

interface CustomerInfoProvider {
    fun getCustomerInfo(customerId: CustomerId): Customer?
}
