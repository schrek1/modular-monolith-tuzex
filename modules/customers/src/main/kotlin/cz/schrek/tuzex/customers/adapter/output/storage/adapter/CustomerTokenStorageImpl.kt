package cz.schrek.tuzex.customers.adapter.output.storage.adapter

import cz.schrek.tuzex.common.annotations.Adapter
import cz.schrek.tuzex.contracts.model.common.TuzexToken
import cz.schrek.tuzex.contracts.model.customers.CustomerId
import cz.schrek.tuzex.customers.adapter.output.storage.memory.entry.CustomerTokenRedisEntry
import cz.schrek.tuzex.customers.adapter.output.storage.memory.repository.CustomerTokenRedisRepository
import cz.schrek.tuzex.customers.appliacation.port.output.CustomerTokenStorage
import java.time.Instant
import kotlin.jvm.optionals.getOrNull

@Adapter
class CustomerTokenStorageImpl(
    private val repository: CustomerTokenRedisRepository
) : CustomerTokenStorage {

    override fun save(customerId: CustomerId, token: TuzexToken) {
        repository.save(
            CustomerTokenRedisEntry(
                customerId = customerId.value,
                token = token.token,
                expiresAt = token.expiresAt ?: Instant.now()
            )
        )
    }

    override fun get(customerId: CustomerId): TuzexToken? =
        repository.findById(customerId.value)
            .map { TuzexToken(token = it.token, expiresAt = it.expiresAt) }
            .getOrNull()
}
