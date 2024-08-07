package cz.schrek.tuzex.customers.adapter.input.webapi

import arrow.core.getOrElse
import cz.schrek.tuzex.common.webapi.WebApiConst
import cz.schrek.tuzex.contracts.model.common.Address
import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.model.common.PhoneNumber
import cz.schrek.tuzex.contracts.modules.customers.NewCustomerRequest
import cz.schrek.tuzex.customers.adapter.input.webapi.dto.CreateCustomerApiRequest
import cz.schrek.tuzex.customers.adapter.input.webapi.dto.CreateCustomerApiResponse
import cz.schrek.tuzex.customers.appliacation.port.input.CreateNewCustomerUseCase
import cz.schrek.tuzex.customers.common.properties.CustomerModuleConfigProperties
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class CustomerController(
    private val createNewCustomerUseCase: CreateNewCustomerUseCase
) {

    @PostMapping("${WebApiConst.API_PREFIX}/${CustomerModuleConfigProperties.MODULE_NAME}/v1/customer")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@Valid @RequestBody request: CreateCustomerApiRequest): CreateCustomerApiResponse =
        createNewCustomerUseCase.createNewCustomer(
            NewCustomerRequest(
                name = request.name,
                surname = request.surname,
                email = Email(request.email),
                phoneNumber = PhoneNumber(prefix = request.phonePrefix, number = request.phoneNumber),
                address = Address(
                    street = request.street,
                    city = request.city,
                    postalCode = request.zipCode,
                    country = request.country
                ),
                password = request.password
            )
        ).map { CreateCustomerApiResponse(it) }.getOrElse { errors ->
            val errorMessage = errors.joinToString { it.description }
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage)
        }
}
