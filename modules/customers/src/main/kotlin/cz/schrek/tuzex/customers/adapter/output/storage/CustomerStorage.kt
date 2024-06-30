package cz.schrek.tuzex.customers.adapter.output.storage

import cz.schrek.tuzex.contracts.model.common.Address
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.model.common.PhoneNumber
import cz.schrek.tuzex.contracts.model.customers.Customer
import cz.schrek.tuzex.customers.appliacation.model.AddressType
import cz.schrek.tuzex.customers.appliacation.model.CustomerContacts
import cz.schrek.tuzex.customers.appliacation.model.CustomerPersonalInfo
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerStorage {


    fun findCustomerByEmailAddress(emailAddress: Email): Customer? {
        return null
    }

    fun findCustomerByPhoneNumber(phoneNumber: PhoneNumber): Customer? {
        return null
    }

    fun saveCustomer(
        customerPersonalInfo: CustomerPersonalInfo,
        customerContacts: CustomerContacts,
        customerAddress: Map<AddressType, Address>
    ): UUID {
        return UUID.randomUUID()
    }

}
