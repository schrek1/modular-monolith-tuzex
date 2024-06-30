package cz.schrek.tuzex.customers.adapter.output.storage.enitity

import cz.schrek.tuzex.customers.appliacation.model.AddressType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
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
    val id: Int = 0,

    @Column(name = "address_type")
    @Enumerated(EnumType.STRING)
    val addressType: AddressType,

    @Column(name = "street")
    val street: String,

    @Column(name = "city")
    val city: String,

    @Column(name = "postal_code")
    val postalCode: String,

    @field:CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: OffsetDateTime = OffsetDateTime.now(),

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: OffsetDateTime = OffsetDateTime.now(),
) {


    fun CustomerAddressEntity.copy(
        id: Int = this.id,
        addressType: AddressType = this.addressType,
        street: String = this.street,
        city: String = this.city,
        postalCode: String = this.postalCode,
        createdAt: OffsetDateTime = this.createdAt,
        updatedAt: OffsetDateTime = this.updatedAt,
    ) = CustomerAddressEntity(
        id = id,
        addressType = addressType,
        street = street,
        city = city,
        postalCode = postalCode,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}
