package cz.schrek.tuzex.customers.adapter.output.storage.db.dao

import cz.schrek.tuzex.customers.adapter.output.storage.db.enitity.CustomerCredentialsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerCredentialsRepository : JpaRepository<CustomerCredentialsEntity, Long> {

    fun findByCustomerId(customerId: Long): CustomerCredentialsEntity?
}
