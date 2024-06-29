package cz.schrek.tuzex.customers.appliacation.model

import cz.schrek.tuzex.contracts.models.common.Email
import cz.schrek.tuzex.contracts.models.common.PhoneNumber

data class CustomerContacts (
    val email: Email,
    val phone: PhoneNumber
)
