package cz.schrek.tuzex.contracts.modules.customers

import arrow.core.Either
import cz.schrek.tuzex.contracts.model.common.Email

interface CustomerLoginProvider {

    fun login(email: Email, password: String): Either<CustomerLoginFailure, CustomerLoginSuccess>
}

data class CustomerLoginSuccess(val token: String)

sealed class CustomerLoginFailure(val description: String) {
    data object EmailNotFound : CustomerLoginFailure("Email not found")
    data object PasswordNotMatch : CustomerLoginFailure("Password doesn't match")
    data object AccountLocked : CustomerLoginFailure("Account locked")
}
