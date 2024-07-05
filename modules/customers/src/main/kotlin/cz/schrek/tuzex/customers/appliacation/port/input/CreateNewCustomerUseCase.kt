package cz.schrek.tuzex.customers.appliacation.port.input

import arrow.core.Either
import cz.schrek.tuzex.contracts.modules.customers.CustomerCreationError
import cz.schrek.tuzex.contracts.modules.customers.NewCustomerRequest
import java.util.*

interface CreateNewCustomerUseCase {
    fun createNewCustomer(request: NewCustomerRequest): Either<List<CustomerCreationError>, UUID>
}
