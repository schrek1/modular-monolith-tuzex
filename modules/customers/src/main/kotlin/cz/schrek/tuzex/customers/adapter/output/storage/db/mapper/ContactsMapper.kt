package cz.schrek.tuzex.customers.adapter.output.storage.db.mapper

import cz.schrek.tuzex.contracts.model.common.Email
import cz.schrek.tuzex.contracts.model.common.PhoneNumber

object ContactsMapper {

    fun Email.toDbValue() = this.value

    fun PhoneNumber.toDbValue() = this.prefix.trim() + " " + this.number.trim()

    fun mapAsEmail(dbValue: String): Email {
        return Email(dbValue)
    }

    fun mapAsPhoneNumber(dbValue: String): PhoneNumber {
        val parts = dbValue.split(" ")
        return PhoneNumber(parts[0], parts[1])
    }
}
