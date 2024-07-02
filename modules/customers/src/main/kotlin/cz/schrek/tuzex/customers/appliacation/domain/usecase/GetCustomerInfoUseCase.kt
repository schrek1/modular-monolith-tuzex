package cz.schrek.tuzex.customers.appliacation.domain.usecase

import cz.schrek.tuzex.common.annotations.UseCase
import cz.schrek.tuzex.contracts.model.common.Address
import cz.schrek.tuzex.customers.appliacation.domain.model.AddressType
import cz.schrek.tuzex.customers.appliacation.domain.model.CustomerContacts
import cz.schrek.tuzex.customers.appliacation.domain.model.CustomerPersonalInfo
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

