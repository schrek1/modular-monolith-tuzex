package cz.schrek.tuzex.contracts.modules.customers

import arrow.core.Either
import cz.schrek.tuzex.contracts.models.common.Address
import cz.schrek.tuzex.contracts.models.common.Email
import cz.schrek.tuzex.contracts.models.common.PhoneNumber
import cz.schrek.tuzex.contracts.models.customer.CustomerId

interface CustomerCreator {
    fun createNewCustomer(request: NewCustomerRequest): Either<List<CustomerCreationError>, CustomerId>
}

sealed class CustomerCreationError(val description: String) {
    data object EmailAlreadyExists : CustomerCreationError("Email already exists")
    data object PhoneNumberAlreadyExists : CustomerCreationError("Phone number already exists")
}

data class NewCustomerRequest(
    val name: String,
    val surname: String,
    val email: Email,
    val phoneNumber: PhoneNumber,
    val address: Address
)
