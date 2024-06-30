package cz.schrek.tuzex.products.config

import cz.schrek.tuzex.common.Const
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
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration("productsDbConfig")
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["${Const.BASE_PACKAGE}.${ProductModuleConfiguration.MODULE_NAME}"],
    entityManagerFactoryRef = "productsEntityManager",
    transactionManagerRef = "productsTransactionManager"
)
class DbConfig(
    private val moduleConfiguration: ProductModuleConfiguration
) {

    @Bean
    fun productsEntityManager(): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = productsPostgres()
        em.setPackagesToScan(*arrayOf("${Const.BASE_PACKAGE}.${ProductModuleConfiguration.MODULE_NAME}"))

        em.jpaVendorAdapter = HibernateJpaVendorAdapter()

        return em
    }

    @Bean
    fun productsTransactionManager(@Qualifier("productsEntityManager") emf: EntityManagerFactory?): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = emf
        return transactionManager
    }

    @Bean
    fun productsPostgres(): DataSource =
        DataSourceBuilder.create()
            .url(moduleConfiguration.datasource.url)
            .username(moduleConfiguration.datasource.username)
            .password(moduleConfiguration.datasource.password)
            .build()

    @Bean(initMethod = "migrate")
    fun productsFlyway(@Qualifier("productsPostgres") dataSource: DataSource): Flyway? = when {
        moduleConfiguration.flyway.enabled -> {
            Flyway.configure()
                .dataSource(dataSource)
                .schemas(moduleConfiguration.datasource.schema)
                .locations(moduleConfiguration.flyway.location)
                .load()
        }

        else -> null
    }
}
