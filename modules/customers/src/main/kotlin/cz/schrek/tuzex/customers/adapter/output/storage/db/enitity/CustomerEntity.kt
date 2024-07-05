package cz.schrek.tuzex.customers.adapter.output.storage.db.enitity

import cz.schrek.tuzex.common.utils.UUIDUtils.nilUuid
import cz.schrek.tuzex.customers.adapter.output.storage.db.enitity.CustomerCredentialsEntity.Companion.copy
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import java.util.*

@Entity
@Table(name = "customers")
class CustomerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int = 0,

    @Column(name = "external_id")
    val externalId: UUID,

    @Column(name = "first_name", length = Integer.MAX_VALUE)
    val firstName: String,

    @Column(name = "last_name", length = Integer.MAX_VALUE)
    val lastName: String,

    @field:CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: OffsetDateTime = OffsetDateTime.now(),

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: OffsetDateTime = OffsetDateTime.now(),

    customerCredentials: CustomerCredentialsEntity? = null,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "customer_id", nullable = false)
    val customerAddresses: List<CustomerAddressEntity> = emptyList(),

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "customer_id", nullable = false)
    val customerContacts: List<CustomerContactEntity> = emptyList(),
) {

    @OneToOne(mappedBy = "customer", cascade = [CascadeType.ALL], orphanRemoval = true)
    val customerCredentials = customerCredentials?.copy(customer = this)

    companion object {
        val EMPTY = CustomerEntity(
            externalId = nilUuid,
            firstName = "",
            lastName = "",
        )

        fun CustomerEntity.copy(
            firstName: String = this.firstName,
            lastName: String = this.lastName,
            customerAddresses: List<CustomerAddressEntity> = this.customerAddresses,
            customerContacts: List<CustomerContactEntity> = this.customerContacts,
        ) = CustomerEntity(
            id = id,
            externalId = externalId,
            firstName = firstName,
            lastName = lastName,
            createdAt = createdAt,
            updatedAt = updatedAt,
            customerCredentials = customerCredentials,
            customerAddresses = customerAddresses,
            customerContacts = customerContacts,
        )
    }
}
