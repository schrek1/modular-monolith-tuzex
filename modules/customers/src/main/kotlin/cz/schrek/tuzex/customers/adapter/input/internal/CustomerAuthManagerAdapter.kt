package cz.schrek.tuzex.customers.adapter.input.internal

import arrow.core.Either
import cz.schrek.tuzex.common.annotations.Adapter
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.model.common.TuzexToken
import cz.schrek.tuzex.contracts.model.customers.CustomerId
import cz.schrek.tuzex.contracts.modules.customers.CustomerAuthManager
import cz.schrek.tuzex.contracts.modules.customers.CustomerLoginFailure
import cz.schrek.tuzex.contracts.modules.customers.CustomerTokenValidationFailure
import cz.schrek.tuzex.customers.appliacation.port.input.CustomerTokenUseCase

@Adapter
class CustomerAuthManagerAdapter(
    private val customerLoginUseCase: CustomerTokenUseCase,
) : CustomerAuthManager {

    override fun generateCustomerToken(
        email: Email,
        password: String,
    ): Either<CustomerLoginFailure, TuzexToken> = customerLoginUseCase.generateToken(email, password)

    override fun isCustomerTokenValid(
        customerId: CustomerId,
        token: TuzexToken
    ): Either<CustomerTokenValidationFailure, TuzexToken> =
        customerLoginUseCase.validateToken(customerId, token)
}
