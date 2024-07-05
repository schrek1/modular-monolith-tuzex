package cz.schrek.tuzex.customers.appliacation.domain.usecase

import arrow.core.Either
import arrow.core.raise.either
import cz.schrek.tuzex.common.annotations.UseCase
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.model.common.TuzexToken
import cz.schrek.tuzex.contracts.model.customers.CustomerId
import cz.schrek.tuzex.contracts.modules.customers.CustomerLoginFailure
import cz.schrek.tuzex.contracts.modules.customers.CustomerTokenValidationFailure
import cz.schrek.tuzex.customers.appliacation.domain.service.PasswordHashService
import cz.schrek.tuzex.customers.appliacation.port.input.CustomerTokenUseCase
import cz.schrek.tuzex.customers.appliacation.port.output.CustomerStorage
import cz.schrek.tuzex.customers.appliacation.port.output.CustomerTokenStorage
import cz.schrek.tuzex.customers.common.properties.CustomerModuleConfigProperties
import java.time.Instant
import java.util.*

@UseCase
internal class CustomerTokenUseCaseImpl(
    private val customerStorage: CustomerStorage,
    private val customerTokenStorage: CustomerTokenStorage,
    private val passwordHashService: PasswordHashService,
    private val moduleConfig: CustomerModuleConfigProperties
) : CustomerTokenUseCase {

    override fun generateToken(email: Email, password: String): Either<CustomerLoginFailure, TuzexToken> = either {
        val customer = customerStorage.findCustomerByEmailAddress(email)
            ?: raise(CustomerLoginFailure.EmailNotFound)

//        val savedPassword = passwordHashService.hashPassword(password)

//        if (savedPassword != hashPassword(password)) raise(CustomerLoginFailure.PasswordNotMatch)

        val token = TuzexToken(
            token = UUID.randomUUID().toString().toCharArray(),
            expiresAt = Instant.now().plus(moduleConfig.token.expiration)
        )

        customerTokenStorage.save(customer.id, token)

        token
    }

    override fun validateToken(
        customerId: CustomerId,
        token: TuzexToken
    ): Either<CustomerTokenValidationFailure, TuzexToken> {
        TODO("Not yet implemented")
    }

    private fun hashPassword(password: String): String {
        return password
    }
}
