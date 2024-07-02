package cz.schrek.tuzex.contracts.model.common

data class PhoneNumber(
    val prefix: String,
    val number: String,
) {
    fun validatePhoneNumber() {
        prefix.matches(PHONE_PREFIX_PATTERN)
        number.matches(PHONE_NUMBER_PATTERN)
    }

    companion object {
        private val PHONE_NUMBER_PATTERN = "^[0-9]{9,14}$".toRegex()
        private val PHONE_PREFIX_PATTERN = "^\\+?[0-9]{1,3}$".toRegex()
    }
}
