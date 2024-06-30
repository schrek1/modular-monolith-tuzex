package cz.schrek.tuzex.customers.adapter.output.storage.dao

import cz.schrek.tuzex.customers.adapter.output.storage.enitity.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository : JpaRepository<CustomerEntity, Long>{

    fun findByExternalId(externalId: UUID): CustomerEntity?
}
