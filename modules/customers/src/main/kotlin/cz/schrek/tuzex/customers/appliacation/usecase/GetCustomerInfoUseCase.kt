package cz.schrek.tuzex.customers.appliacation.usecase

import cz.schrek.tuzex.common.annotations.UseCase
import cz.schrek.tuzex.contracts.models.common.Address
import cz.schrek.tuzex.customers.appliacation.model.AddressType
import cz.schrek.tuzex.customers.appliacation.model.CustomerContacts
import cz.schrek.tuzex.customers.appliacation.model.CustomerPersonalInfo
import java.util.*

@UseCase
class GetCustomerInfoUseCase {
    fun getCustomerPersonalInfo(customerId: UUID): CustomerPersonalInfo {
        TODO("Not yet implemented")
    }

    fun getCustomerAddress(customerId: UUID, addressType: AddressType): Address {
        TODO("Not yet implemented")
    }

    fun getCustomerContacts(customerId: UUID): CustomerContacts {
        TODO("Not yet implemented")
    }

}

