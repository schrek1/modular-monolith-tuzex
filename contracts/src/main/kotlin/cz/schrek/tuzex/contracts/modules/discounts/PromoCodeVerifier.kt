package cz.schrek.tuzex.contracts.modules.discounts

interface PromoCodeVerifier {

    fun verifyPromoCode(
        customerId: String,
        cartId: String,
        promoCode: String,
    ): PromoCodeVerificationResult
}


sealed class PromoCodeVerificationResult {
    data object Valid : PromoCodeVerificationResult()
    data class Invalid(val reason: String) : PromoCodeVerificationResult()
    data class Expired(val reason: String) : PromoCodeVerificationResult()
}



