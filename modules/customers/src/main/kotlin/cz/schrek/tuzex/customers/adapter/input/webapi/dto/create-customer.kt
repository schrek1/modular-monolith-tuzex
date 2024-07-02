package cz.schrek.tuzex.customers.adapter.input.webapi.dto

import cz.schrek.tuzex.common.validations.ValidPhoneNumber
import cz.schrek.tuzex.common.validations.ValidPhonePrefix
import cz.schrek.tuzex.contracts.model.common.Password
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.*

data class CreateCustomerApiRequest(
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val surname: String,
    @field:Email
    val email: String,
    @field:ValidPhonePrefix
    val phonePrefix: String,
    @field:ValidPhoneNumber
    val phoneNumber: String,
    @field:NotBlank
    val street: String,
    @field:NotBlank
    val city: String,
    @field:NotBlank
    val zipCode: String,
    @field:NotBlank
    val country: String,
    @field:NotNull
    val password: Password
)

data class CreateCustomerApiResponse(
    val customerId: UUID
)
