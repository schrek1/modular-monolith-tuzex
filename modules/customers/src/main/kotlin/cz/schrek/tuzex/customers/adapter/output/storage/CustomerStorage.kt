package cz.schrek.tuzex.customers.adapter.output.storage

import cz.schrek.tuzex.contracts.model.common.Address
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.model.common.PhoneNumber
import cz.schrek.tuzex.contracts.model.customers.Customer
import cz.schrek.tuzex.customers.adapter.output.storage.dao.CustomerContactRepository
import cz.schrek.tuzex.customers.adapter.output.storage.dao.CustomerCredentialsRepository
import cz.schrek.tuzex.customers.adapter.output.storage.dao.CustomerRepository
import cz.schrek.tuzex.customers.adapter.output.storage.enitity.CustomerAddressEntity
import cz.schrek.tuzex.customers.adapter.output.storage.enitity.CustomerContactEntity
import cz.schrek.tuzex.customers.adapter.output.storage.enitity.CustomerCredentialsEntity
import cz.schrek.tuzex.customers.adapter.output.storage.enitity.CustomerEntity
import cz.schrek.tuzex.customers.adapter.output.storage.mapper.ContactsMapper.toDbValue
import cz.schrek.tuzex.customers.adapter.output.storage.mapper.CustomerMapper.toDomain
import cz.schrek.tuzex.customers.appliacation.model.AddressType
import cz.schrek.tuzex.customers.appliacation.model.CustomerContacts
import cz.schrek.tuzex.customers.appliacation.model.CustomerCredentials
import cz.schrek.tuzex.customers.appliacation.model.CustomerPersonalInfo
import cz.schrek.tuzex.customers.config.DbConfig
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class CustomerStorage(
    private val customerRepository: CustomerRepository,
    private val customerCredentialsRepository: CustomerCredentialsRepository,
    private val customerContactRepository: CustomerContactRepository
) {

    fun findCustomerByEmailAddress(emailAddress: Email): Customer? =
        customerContactRepository.findByContactTypeAndValue(
            contactType = CustomerContactEntity.ContactType.EMAIL,
            value = emailAddress.toDbValue()
        )?.customer?.toDomain()

    fun findCustomerByPhoneNumber(phoneNumber: PhoneNumber): Customer? =
        customerContactRepository.findByContactTypeAndValue(
            contactType = CustomerContactEntity.ContactType.PHONE_NUMBER,
            value = phoneNumber.toDbValue()
        )?.customer?.toDomain()

    @Transactional(transactionManager = DbConfig.MODULE_TRANSACTION_MANAGER)
    fun saveCustomer(
        customerPersonalInfo: CustomerPersonalInfo,
        customerContacts: CustomerContacts,
        customerAddress: Map<AddressType, Address>,
        customerCredentials: CustomerCredentials
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
                    postalCode = address.postalCode,
                    country = address.country
                )
            },
            customerCredentials = CustomerCredentialsEntity(
                hashedPassword = customerCredentials.hashedPassword
            )
        )

        return customerRepository.save(customer).externalId
    }

}
