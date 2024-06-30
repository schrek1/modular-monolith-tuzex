package cz.schrek.tuzex.customers.adapter.input.webapi

import arrow.core.getOrElse
import cz.schrek.tuzex.common.webapi.WebApiConst
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.customers.adapter.input.webapi.dto.CustomerLoginApiRequest
import cz.schrek.tuzex.customers.adapter.input.webapi.dto.CustomerLoginApiResponse
import cz.schrek.tuzex.customers.appliacation.usecase.CustomerLoginUseCase
import cz.schrek.tuzex.customers.config.CustomerModuleConfiguration
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class CustomerLoginController(
    private val customerLoginUseCase: CustomerLoginUseCase
) {

    @PostMapping("${WebApiConst.API_PREFIX}/v1/${CustomerModuleConfiguration.MODULE_NAME}/customer/login")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@Valid @RequestBody request: CustomerLoginApiRequest): CustomerLoginApiResponse =
        customerLoginUseCase.login(
            email = Email(request.email),
            password = request.password
        ).map { CustomerLoginApiResponse(it.token) }.getOrElse { error ->
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, error.description)
        }
}
