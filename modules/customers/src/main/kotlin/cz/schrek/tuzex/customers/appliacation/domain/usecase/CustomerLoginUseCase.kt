package cz.schrek.tuzex.customers.appliacation.domain.usecase

import arrow.core.Either
import arrow.core.raise.either
import cz.schrek.tuzex.common.annotations.UseCase
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.modules.customers.CustomerLoginFailure
import cz.schrek.tuzex.contracts.modules.customers.CustomerLoginProvider
import cz.schrek.tuzex.contracts.modules.customers.CustomerLoginSuccess
import cz.schrek.tuzex.customers.adapter.output.storage.CustomerLoginStorage
import cz.schrek.tuzex.customers.adapter.output.storage.CustomerStorage
import java.util.*

@UseCase
class CustomerLoginUseCase(
    private val customerStorage: CustomerStorage,
    private val customerLoginStorage: CustomerLoginStorage
) : CustomerLoginProvider {

    override fun login(email: Email, password: String): Either<CustomerLoginFailure, CustomerLoginSuccess> = either {
        val customer = customerStorage.findCustomerByEmailAddress(email)
            ?: raise(CustomerLoginFailure.EmailNotFound)

        val savedPassword = customerLoginStorage.getCustomerHashedPassword(customer.id.value)

        if (savedPassword != hashPassword(password)) raise(CustomerLoginFailure.PasswordNotMatch)

        CustomerLoginSuccess(token = UUID.randomUUID().toString())
    }

    private fun hashPassword(password: String): String {
        return password
    }
}
