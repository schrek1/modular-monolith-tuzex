package cz.schrek.tuzex.contracts.model.customers

import cz.schrek.tuzex.contracts.model.common.Address
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.model.common.PhoneNumber

data class Customer(
    val id: CustomerId,
    val name: String,
    val surname: String,
    val email: Email,
    val phoneNumber: PhoneNumber,
    val address: Address,
)
