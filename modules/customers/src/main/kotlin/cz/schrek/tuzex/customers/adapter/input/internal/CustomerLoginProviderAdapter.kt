package cz.schrek.tuzex.customers.adapter.input.internal

import arrow.core.Either
import cz.schrek.tuzex.common.annotations.Adapter
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.modules.customers.CustomerLoginFailure
import cz.schrek.tuzex.contracts.modules.customers.CustomerLoginProvider
import cz.schrek.tuzex.contracts.modules.customers.CustomerLoginSuccess
import cz.schrek.tuzex.customers.appliacation.usecase.CustomerLoginUseCase

@Adapter
class CustomerLoginProviderAdapter(
    private val customerLoginUseCase: CustomerLoginUseCase
) : CustomerLoginProvider {

    override fun login(email: Email, password: String): Either<CustomerLoginFailure, CustomerLoginSuccess> {
        return customerLoginUseCase.login(email, password)
    }
}
