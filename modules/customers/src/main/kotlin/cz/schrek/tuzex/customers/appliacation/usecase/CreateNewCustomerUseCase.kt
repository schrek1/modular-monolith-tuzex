package cz.schrek.tuzex.customers.appliacation.usecase

import arrow.core.Either
import arrow.core.raise.either
import cz.schrek.tuzex.common.annotations.UseCase
import cz.schrek.tuzex.contracts.modules.customers.CustomerCreationError
import cz.schrek.tuzex.contracts.modules.customers.NewCustomerRequest
import cz.schrek.tuzex.customers.adapter.output.storage.CustomerStorage
import cz.schrek.tuzex.customers.appliacation.model.AddressType
import cz.schrek.tuzex.customers.appliacation.model.CustomerContacts
import cz.schrek.tuzex.customers.appliacation.model.CustomerPersonalInfo
import java.util.*

@UseCase
class CreateNewCustomerUseCase(
    private val customerStorage: CustomerStorage
) {

    fun createNewCustomer(request: NewCustomerRequest): Either<List<CustomerCreationError>, UUID> = either {
        validateThatCustomerDoesNotExist(request).takeUnless { it.isEmpty() }?.let { raise(it) }

        customerStorage.saveCustomer(
            customerPersonalInfo = CustomerPersonalInfo(name = request.name, surname = request.surname),
            customerContacts = CustomerContacts(email = request.email, phone = request.phoneNumber),
            customerAddress = mapOf(AddressType.PRIMARY to request.address)
        )
    }

    private fun validateThatCustomerDoesNotExist(request: NewCustomerRequest): List<CustomerCreationError> = buildList {
        if (customerStorage.findCustomerByEmailAddress(request.email) != null) {
            add(CustomerCreationError.EmailAlreadyExists)
        }
        if (customerStorage.findCustomerByPhoneNumber(request.phoneNumber) != null) {
            add(CustomerCreationError.PhoneNumberAlreadyExists)
        }
    }
}
