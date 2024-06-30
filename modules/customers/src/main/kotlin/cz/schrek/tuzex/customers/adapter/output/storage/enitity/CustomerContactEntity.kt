package cz.schrek.tuzex.customers.adapter.output.storage.enitity

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
@Table(name = "customer_contacts")
class CustomerContactEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int = 0,

    @Column(name = "contact_type")
    @Enumerated(EnumType.STRING)
    val contactType: ContactType,

    @Column(name = "value")
    val value: String,

    @field:CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: OffsetDateTime = OffsetDateTime.now(),

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: OffsetDateTime = OffsetDateTime.now(),
) {

    fun CustomerContactEntity.copy(
        id: Int = this.id,
        contactType: ContactType = this.contactType,
        value: String = this.value,
        createdAt: OffsetDateTime = this.createdAt,
        updatedAt: OffsetDateTime = this.updatedAt,
    ) = CustomerContactEntity(
        id = id,
        contactType = contactType,
        value = value,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

    enum class ContactType {
        EMAIL,
        PHONE_NUMBER
    }
}

