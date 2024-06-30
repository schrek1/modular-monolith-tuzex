package cz.schrek.tuzex.customers.adapter.input.webapi.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CustomerLoginApiRequest(
    @field:Email
    val email: String,
    @field:NotBlank
    val password: String
)


data class CustomerLoginApiResponse(
    val token: String
)
