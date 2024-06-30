package cz.schrek.tuzex.contracts.model.common

@JvmInline
value class Email(val value: String) {
    init {
        require(value.isNotBlank()) { "Email must not be blank" }
        require(value.length <= 255) { "Email must not exceed 255 characters" }
        require(value.matches(EMAIL_REGEX)) { "Email must be valid" }
    }

    companion object {
        private val EMAIL_REGEX = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
    }
}
