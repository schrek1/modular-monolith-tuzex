package cz.schrek.tuzex.contracts.modules.customers

import arrow.core.Either
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.model.common.TuzexToken
import cz.schrek.tuzex.contracts.model.customers.CustomerId

interface CustomerAuthManager {

    fun generateCustomerToken(email: Email, password: String): Either<CustomerLoginFailure, TuzexToken>

    fun isCustomerTokenValid(
        customerId: CustomerId,
        token: TuzexToken
    ): Either<CustomerTokenValidationFailure, TuzexToken>
}

sealed class CustomerLoginFailure(val description: String) {
    data object EmailNotFound : CustomerLoginFailure("Email not found")
    data object PasswordNotMatch : CustomerLoginFailure("Password doesn't match")
    data object AccountLocked : CustomerLoginFailure("Account locked")
}

sealed class CustomerTokenValidationFailure(val description: String) {
    data object CustomerNotExists : CustomerTokenValidationFailure("Customer doesn't exist")
    data object TokenNotValid : CustomerTokenValidationFailure("Token not valid")
}
