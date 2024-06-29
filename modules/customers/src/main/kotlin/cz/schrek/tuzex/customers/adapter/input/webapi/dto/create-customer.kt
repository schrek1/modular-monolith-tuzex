package cz.schrek.tuzex.customers.adapter.input.webapi.dto

import cz.schrek.tuzex.common.webapi.validations.ValidPhoneNumber
import cz.schrek.tuzex.common.webapi.validations.ValidPhonePrefix
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
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
)

data class CreateCustomerApiResponse(
    val customerId: UUID
)
