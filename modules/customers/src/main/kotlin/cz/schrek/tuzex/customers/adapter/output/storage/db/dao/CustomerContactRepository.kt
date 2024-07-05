package cz.schrek.tuzex.customers.adapter.output.storage.db.dao

import cz.schrek.tuzex.customers.adapter.output.storage.db.enitity.CustomerContactEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerContactRepository : JpaRepository<CustomerContactEntity, Long> {

    fun findByContactTypeAndValue(
        contactType: CustomerContactEntity.ContactType,
        value: String
    ): CustomerContactEntity?
}
