package cz.schrek.tuzex.customers.adapter.output.storage.enitity

import cz.schrek.tuzex.customers.appliacation.model.AddressType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime

@Entity
@Table(name = "customer_addresses")
class CustomerAddressEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id", nullable = false, insertable = false, updatable = false)
    val customer: CustomerEntity? = null,

    @Column(name = "address_type")
    @Enumerated(EnumType.STRING)
    val addressType: AddressType,

    @Column(name = "street")
    val street: String,

    @Column(name = "city")
    val city: String,

    @Column(name = "postal_code")
    val postalCode: String,

    @Column(name = "country")
    val country: String,

    @field:CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: OffsetDateTime = OffsetDateTime.now(),

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: OffsetDateTime = OffsetDateTime.now(),
) {

    companion object {
        fun CustomerAddressEntity.copy(
            addressType: AddressType = this.addressType,
            street: String = this.street,
            city: String = this.city,
            postalCode: String = this.postalCode,
            country: String = this.country,
        ) = CustomerAddressEntity(
            id = id,
            customer = customer,
            addressType = addressType,
            street = street,
            city = city,
            postalCode = postalCode,
            country = country,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}
