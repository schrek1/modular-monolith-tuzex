package cz.schrek.tuzex.customers.appliacation.port.output

import cz.schrek.tuzex.contracts.model.common.TuzexToken
import cz.schrek.tuzex.contracts.model.customers.CustomerId

interface CustomerTokenStorage {

    fun save(customerId: CustomerId, token: TuzexToken)

    fun get(customerId: CustomerId): TuzexToken?
}
