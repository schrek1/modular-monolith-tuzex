package cz.schrek.tuzex.customers.adapter.output.storage.db.mapper

import cz.schrek.tuzex.contracts.model.common.Address
import cz.schrek.tuzex.customers.adapter.output.storage.db.enitity.CustomerAddressEntity

object AddressMapper {

    fun CustomerAddressEntity.toDomain() = Address(
        street = this.street,
        city = this.city,
        postalCode = this.postalCode,
        country = this.country
    )
}
