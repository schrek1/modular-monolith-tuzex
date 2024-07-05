package cz.schrek.tuzex.customers.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

@ConfigurationProperties(prefix = "modules.customers")
data class CustomerModuleConfigProperties(
    val postgres: PostgresConfiguration,
    val flyway: FlywayConfiguration,
    val redis: RedisConfiguration,
    val token: TokenConfiguration
) {
    companion object {
        const val MODULE_NAME = "customers"
    }
}

data class TokenConfiguration(
    val expiration: Duration
)

data class RedisConfiguration(
    val host: String,
    val port: Int,
    val username: String?,
    val password: String
)


data class PostgresConfiguration(
    val url: String,
    val username: String,
    val password: String,
    val schema: String
)

data class FlywayConfiguration(
    val enabled: Boolean,
    val location: String
)
