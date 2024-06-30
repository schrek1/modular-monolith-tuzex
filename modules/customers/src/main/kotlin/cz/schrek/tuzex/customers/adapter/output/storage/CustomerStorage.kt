package cz.schrek.tuzex.customers.adapter.output.storage

import cz.schrek.tuzex.contracts.model.common.Address
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.model.common.PhoneNumber
import cz.schrek.tuzex.contracts.model.customers.Customer
import cz.schrek.tuzex.customers.adapter.output.storage.dao.CustomerRepository
import cz.schrek.tuzex.customers.adapter.output.storage.enitity.CustomerAddressEntity
import cz.schrek.tuzex.customers.adapter.output.storage.enitity.CustomerContactEntity
import cz.schrek.tuzex.customers.adapter.output.storage.enitity.CustomerEntity
import cz.schrek.tuzex.customers.adapter.output.storage.mapper.ContactsMapper.toDbValue
import cz.schrek.tuzex.customers.appliacation.model.AddressType
import cz.schrek.tuzex.customers.appliacation.model.CustomerContacts
import cz.schrek.tuzex.customers.appliacation.model.CustomerPersonalInfo
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerStorage(
    private val customerRepository: CustomerRepository
) {

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
        val customer = CustomerEntity(
            externalId = UUID.randomUUID(),
            firstName = customerPersonalInfo.name,
            lastName = customerPersonalInfo.surname,
            customerContacts = listOf(
                CustomerContactEntity(
                    contactType = CustomerContactEntity.ContactType.EMAIL,
                    value = customerContacts.email.toDbValue()
                ),
                CustomerContactEntity(
                    contactType = CustomerContactEntity.ContactType.PHONE_NUMBER,
                    value = customerContacts.phone.toDbValue()
                )
            ),
            customerAddresses = customerAddress.map { (type, address) ->
                CustomerAddressEntity(
                    addressType = type,
                    street = address.street,
                    city = address.city,
                    postalCode = address.postalCode
                )
            }
        )

        customerRepository.save(customer)

        return customer.externalId
    }

}
