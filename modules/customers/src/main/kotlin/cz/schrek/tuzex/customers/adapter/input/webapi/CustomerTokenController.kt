package cz.schrek.tuzex.customers.adapter.input.webapi

import arrow.core.getOrElse
import cz.schrek.tuzex.common.webapi.WebApiConst
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.customers.adapter.input.webapi.dto.CustomerLoginApiRequest
import cz.schrek.tuzex.customers.adapter.input.webapi.dto.CustomerLoginApiResponse
import cz.schrek.tuzex.customers.appliacation.port.input.CustomerTokenUseCase
import cz.schrek.tuzex.customers.common.properties.CustomerModuleConfigProperties
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class CustomerTokenController(
    private val customerLoginUseCase: CustomerTokenUseCase
) {

    @PostMapping("${WebApiConst.API_PREFIX}/v1/${CustomerModuleConfigProperties.MODULE_NAME}/customer/token")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomerToken(@Valid @RequestBody request: CustomerLoginApiRequest): CustomerLoginApiResponse =
        customerLoginUseCase.generateToken(
            email = Email(request.email),
            password = request.password
        ).map { CustomerLoginApiResponse(it.token.toString()) }.getOrElse { error ->
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, error.description)
        }
}
