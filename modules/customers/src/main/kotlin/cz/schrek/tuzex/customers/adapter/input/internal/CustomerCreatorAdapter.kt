package cz.schrek.tuzex.customers.adapter.input.internal

import arrow.core.Either
import cz.schrek.tuzex.common.annotations.Adapter
import cz.schrek.tuzex.contracts.model.customers.CustomerId
import cz.schrek.tuzex.contracts.modules.customers.CustomerCreationError
import cz.schrek.tuzex.contracts.modules.customers.CustomerCreator
import cz.schrek.tuzex.contracts.modules.customers.NewCustomerRequest
import cz.schrek.tuzex.customers.appliacation.usecase.CreateNewCustomerUseCase

@Adapter
class CustomerCreatorAdapter(
    private val useCase: CreateNewCustomerUseCase
) : CustomerCreator {

    override fun createNewCustomer(request: NewCustomerRequest): Either<List<CustomerCreationError>, CustomerId> =
        useCase.createNewCustomer(request).map { CustomerId(it) }
}
