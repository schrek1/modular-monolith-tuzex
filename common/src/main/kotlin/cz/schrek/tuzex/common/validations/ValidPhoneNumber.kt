package cz.schrek.tuzex.common.validations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [PhoneNumberValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidPhoneNumber(
    val message: String = "Invalid phone number",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class PhoneNumberValidator : ConstraintValidator<ValidPhoneNumber, String> {

    override fun isValid(phoneNumber: String?, context: ConstraintValidatorContext): Boolean =
        phoneNumber != null && PHONE_NUMBER_PATTERN.matches(phoneNumber)

    companion object {
        private val PHONE_NUMBER_PATTERN = "^[0-9]{9,14}$".toRegex()
    }
}
