package cz.schrek.tuzex.customers.adapter.input.internal

import cz.schrek.tuzex.common.annotations.Adapter
import cz.schrek.tuzex.contracts.model.common.Address
import cz.schrek.tuzex.contracts.modules.customers.CustomerModifier
import java.util.*

@Adapter
class CustomerModifierAdapter : CustomerModifier {
    override fun changeAddress(customerId: UUID, address: Address) {
        TODO("Not yet implemented")
    }
}
