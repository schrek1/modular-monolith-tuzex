package cz.schrek.tuzex.customers.config

import org.springframework.context.annotation.PropertySource

@PropertySource("classpath:module-customers-config.yaml")
data class CustomerModuleConfiguration(
    val foo: String
) {
    companion object {
        const val MODULE_NAME = "customers"
    }
}
