package cz.schrek.tuzex.customers.adapter.output.storage.mapper

import cz.schrek.tuzex.contracts.model.customers.Customer
import cz.schrek.tuzex.contracts.model.customers.CustomerId
import cz.schrek.tuzex.customers.adapter.output.storage.enitity.CustomerContactEntity
import cz.schrek.tuzex.customers.adapter.output.storage.enitity.CustomerEntity
import cz.schrek.tuzex.customers.adapter.output.storage.mapper.AddressMapper.toDomain
import cz.schrek.tuzex.customers.adapter.output.storage.mapper.ContactsMapper.mapAsEmail
import cz.schrek.tuzex.customers.adapter.output.storage.mapper.ContactsMapper.mapAsPhoneNumber

object CustomerMapper {

    fun CustomerEntity.toDomain() = Customer(
        id = CustomerId(externalId),
        name = firstName,
        surname = lastName,
        email = mapAsEmail(customerContacts.first { it.contactType == CustomerContactEntity.ContactType.EMAIL }.value),
        phoneNumber = mapAsPhoneNumber(customerContacts.first { it.contactType == CustomerContactEntity.ContactType.PHONE_NUMBER }.value),
        address = customerAddresses.first().toDomain()
    )
}
