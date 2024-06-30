package cz.schrek.tuzex.customers.adapter.output.storage

import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerLoginStorage {

    fun getCustomerHashedPassword(customerId: UUID): String {
        return "customer-credentials"
    }
}
