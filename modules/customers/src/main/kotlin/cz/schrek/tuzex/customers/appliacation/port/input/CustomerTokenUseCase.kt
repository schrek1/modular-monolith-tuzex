package cz.schrek.tuzex.customers.appliacation.port.input

import arrow.core.Either
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.model.common.TuzexToken
import cz.schrek.tuzex.contracts.model.customers.CustomerId
import cz.schrek.tuzex.contracts.modules.customers.CustomerLoginFailure
import cz.schrek.tuzex.contracts.modules.customers.CustomerTokenValidationFailure

interface CustomerTokenUseCase {
    fun generateToken(email: Email, password: String): Either<CustomerLoginFailure, TuzexToken>

    fun validateToken(customerId: CustomerId, token: TuzexToken): Either<CustomerTokenValidationFailure, TuzexToken>
}
