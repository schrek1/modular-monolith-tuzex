package cz.schrek.tuzex.contracts.modules.customers

import cz.schrek.tuzex.contracts.models.common.Address
import java.util.*

interface CustomerModifier {
    fun changeAddress(customerId: UUID, address: Address)
}
