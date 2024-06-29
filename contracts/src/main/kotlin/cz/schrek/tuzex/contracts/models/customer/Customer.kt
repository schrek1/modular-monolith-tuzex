package cz.schrek.tuzex.contracts.models.customer

import cz.schrek.tuzex.contracts.models.common.Address
import cz.schrek.tuzex.contracts.models.common.Email
import cz.schrek.tuzex.contracts.models.common.PhoneNumber

data class Customer(
    val id: CustomerId,
    val name: String,
    val surname: String,
    val email: Email,
    val phoneNumber: PhoneNumber,
    val address: Address,
)
