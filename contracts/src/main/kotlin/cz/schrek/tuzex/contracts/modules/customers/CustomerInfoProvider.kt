package cz.schrek.tuzex.contracts.modules.customers

import cz.schrek.tuzex.contracts.model.customers.Customer
import cz.schrek.tuzex.contracts.model.customers.CustomerId

interface CustomerInfoProvider {
    fun getCustomerInfo(customerId: CustomerId): Customer?
}
