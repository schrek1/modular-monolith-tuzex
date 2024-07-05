package cz.schrek.tuzex.customers.config

import cz.schrek.tuzex.common.Const
import cz.schrek.tuzex.customers.common.properties.CustomerModuleConfigProperties
import jakarta.persistence.EntityManagerFactory
import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.AbstractTransactionManagementConfiguration
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration("customersDbConfig")
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["${Const.BASE_PACKAGE}.${CustomerModuleConfigProperties.MODULE_NAME}"],
    entityManagerFactoryRef = "customersEntityManager",
    transactionManagerRef = "customersTransactionManager"
)
class DbConfig(
    private val moduleConfiguration: CustomerModuleConfigProperties
) : AbstractTransactionManagementConfiguration() {

    companion object {
        const val MODULE_TRANSACTION_MANAGER = "customersTransactionManager"
    }

    @Bean("customersEntityManager")
    fun customersEntityManager(): LocalContainerEntityManagerFactoryBean =
        LocalContainerEntityManagerFactoryBean().apply {
            dataSource = customersPostgres()
            setPackagesToScan("${Const.BASE_PACKAGE}.${CustomerModuleConfigProperties.MODULE_NAME}")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
            setJpaPropertyMap(buildMap {
                put("hibernate.default_schema", moduleConfiguration.postgres.schema)
            })
        }

    @Bean(MODULE_TRANSACTION_MANAGER)
    fun customersTransactionManager(@Qualifier("customersEntityManager") emf: EntityManagerFactory): PlatformTransactionManager =
        JpaTransactionManager().apply { entityManagerFactory = emf }

    @Bean
    fun customersPostgres(): DataSource =
        DataSourceBuilder.create()
            .url(moduleConfiguration.postgres.url)
            .username(moduleConfiguration.postgres.username)
            .password(moduleConfiguration.postgres.password)
            .build()

    @Bean(initMethod = "migrate")
    fun customersFlyway(@Qualifier("customersPostgres") dataSource: DataSource): Flyway? = when {
        moduleConfiguration.flyway.enabled -> {
            Flyway.configure()
                .dataSource(dataSource)
                .schemas(moduleConfiguration.postgres.schema)
                .locations(moduleConfiguration.flyway.location)
                .load()
        }

        else -> null
    }
}
