package cz.schrek.tuzex.customers.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "modules.customers")
data class CustomerModuleConfiguration(
    val datasource: DataSourceConfiguration,
    val flyway: FlywayConfiguration
) {
    companion object {
        const val MODULE_NAME = "customers"
    }
}


data class DataSourceConfiguration(
    val url: String,
    val username: String,
    val password: String,
    val schema: String
)

data class FlywayConfiguration(
    val enabled: Boolean,
    val location: String
)
