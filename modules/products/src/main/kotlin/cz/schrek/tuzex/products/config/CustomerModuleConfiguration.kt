package cz.schrek.tuzex.products.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "modules.products")
data class ProductModuleConfiguration(
    val datasource: DataSourceConfiguration,
    val flyway: FlywayConfiguration
) {
    companion object {
        const val MODULE_NAME = "products"
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
