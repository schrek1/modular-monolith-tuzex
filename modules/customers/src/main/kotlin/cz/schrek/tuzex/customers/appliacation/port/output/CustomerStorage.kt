package cz.schrek.tuzex.customers.appliacation.port.output

import cz.schrek.tuzex.contracts.model.common.Address
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.model.common.PhoneNumber
import cz.schrek.tuzex.contracts.model.customers.Customer
import cz.schrek.tuzex.customers.appliacation.domain.model.AddressType
import cz.schrek.tuzex.customers.appliacation.domain.model.CustomerContacts
import cz.schrek.tuzex.customers.appliacation.domain.model.CustomerCredentials
import cz.schrek.tuzex.customers.appliacation.domain.model.CustomerPersonalInfo
import java.util.*

interface CustomerStorage {
    fun findCustomerByEmailAddress(emailAddress: Email): Customer?

    fun findCustomerByPhoneNumber(phoneNumber: PhoneNumber): Customer?

    fun saveCustomer(
        customerPersonalInfo: CustomerPersonalInfo,
        customerContacts: CustomerContacts,
        customerAddress: Map<AddressType, Address>,
        customerCredentials: CustomerCredentials
    ): UUID
}
