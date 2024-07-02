package cz.schrek.tuzex.customers.adapter.output.storage.dao

import cz.schrek.tuzex.customers.adapter.output.storage.enitity.CustomerCredentialsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerCredentialsRepository : JpaRepository<CustomerCredentialsEntity, Long> {

    fun findByCustomerId(customerId: Long): CustomerCredentialsEntity?
}
