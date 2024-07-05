package cz.schrek.tuzex.customers.adapter.output.storage.adapter

import cz.schrek.tuzex.common.annotations.Adapter
import cz.schrek.tuzex.contracts.model.common.Address
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.model.common.PhoneNumber
import cz.schrek.tuzex.contracts.model.customers.Customer
import cz.schrek.tuzex.customers.adapter.output.storage.db.dao.CustomerContactRepository
import cz.schrek.tuzex.customers.adapter.output.storage.db.dao.CustomerCredentialsRepository
import cz.schrek.tuzex.customers.adapter.output.storage.db.dao.CustomerRepository
import cz.schrek.tuzex.customers.adapter.output.storage.db.enitity.CustomerAddressEntity
import cz.schrek.tuzex.customers.adapter.output.storage.db.enitity.CustomerContactEntity
import cz.schrek.tuzex.customers.adapter.output.storage.db.enitity.CustomerCredentialsEntity
import cz.schrek.tuzex.customers.adapter.output.storage.db.enitity.CustomerEntity
import cz.schrek.tuzex.customers.adapter.output.storage.db.mapper.ContactsMapper.toDbValue
import cz.schrek.tuzex.customers.adapter.output.storage.db.mapper.CustomerMapper.toDomain
import cz.schrek.tuzex.customers.appliacation.domain.model.AddressType
import cz.schrek.tuzex.customers.appliacation.domain.model.CustomerContacts
import cz.schrek.tuzex.customers.appliacation.domain.model.CustomerCredentials
import cz.schrek.tuzex.customers.appliacation.domain.model.CustomerPersonalInfo
import cz.schrek.tuzex.customers.appliacation.port.output.CustomerStorage
import cz.schrek.tuzex.customers.config.DbConfig
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Adapter
internal class CustomerStorageImpl(
    private val customerRepository: CustomerRepository,
    private val customerCredentialsRepository: CustomerCredentialsRepository,
    private val customerContactRepository: CustomerContactRepository
) : CustomerStorage {

    override fun findCustomerByEmailAddress(emailAddress: Email): Customer? =
        customerContactRepository.findByContactTypeAndValue(
            contactType = CustomerContactEntity.ContactType.EMAIL,
            value = emailAddress.toDbValue()
        )?.customer?.toDomain()

    override fun findCustomerByPhoneNumber(phoneNumber: PhoneNumber): Customer? =
        customerContactRepository.findByContactTypeAndValue(
            contactType = CustomerContactEntity.ContactType.PHONE_NUMBER,
            value = phoneNumber.toDbValue()
        )?.customer?.toDomain()

    @Transactional(transactionManager = DbConfig.MODULE_TRANSACTION_MANAGER)
    override fun saveCustomer(
        customerPersonalInfo: CustomerPersonalInfo,
        customerContacts: CustomerContacts,
        customerAddress: Map<AddressType, Address>,
        customerCredentials: CustomerCredentials
    ): UUID {
        val newCustomer = CustomerEntity(
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

        return customerRepository.save(newCustomer).externalId
    }

}
