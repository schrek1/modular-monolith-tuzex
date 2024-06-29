package cz.schrek.tuzex.common.webapi.validations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [PhonePrefixValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidPhonePrefix(
    val message: String = "Invalid phone prefix",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class PhonePrefixValidator : ConstraintValidator<ValidPhonePrefix, String> {

    override fun isValid(prefix: String?, context: ConstraintValidatorContext): Boolean =
        prefix != null && PHONE_PREFIX_PATTERN.matches(prefix)

    companion object {
        private val PHONE_PREFIX_PATTERN = "^\\+?[0-9]{1,3}$".toRegex()
    }
}
