package cz.schrek.tuzex.customers.appliacation.domain.model

import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.model.common.PhoneNumber

data class CustomerContacts (
    val email: Email,
    val phone: PhoneNumber
)
