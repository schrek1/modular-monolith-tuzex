package cz.schrek.tuzex.customers.adapter.output.storage.memory.entry

import cz.schrek.tuzex.common.Const
import cz.schrek.tuzex.customers.common.properties.CustomerModuleConfigProperties
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable
import java.time.Instant
import java.util.*

@RedisHash("${Const.APPLICATION_NAME}.${CustomerModuleConfigProperties.MODULE_NAME}.customer_token")
data class CustomerTokenRedisEntry(
    @Id
    val customerId: UUID,
    val token: CharArray,
    val expiresAt: Instant
) : Serializable
