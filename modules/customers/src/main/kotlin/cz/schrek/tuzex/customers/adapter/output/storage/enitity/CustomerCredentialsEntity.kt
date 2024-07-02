package cz.schrek.tuzex.customers.adapter.output.storage.enitity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime

@Entity
@Table(name = "customer_credentials")
class CustomerCredentialsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    var customer: CustomerEntity? = null,

    @Column(name = "password")
    val hashedPassword: String,

    @field:CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: OffsetDateTime = OffsetDateTime.now(),

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: OffsetDateTime = OffsetDateTime.now(),
) {
    companion object {
        fun CustomerCredentialsEntity.copy(
            hashedPassword: String = this.hashedPassword,
            customer: CustomerEntity? = this.customer,
        ) = CustomerCredentialsEntity(
            id = id,
            customer = customer,
            hashedPassword = hashedPassword,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}
