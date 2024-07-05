package cz.schrek.tuzex.customers.config

import cz.schrek.tuzex.customers.common.properties.CustomerModuleConfigProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory


@Configuration
class RedisConfig(
    private val moduleConfiguration: CustomerModuleConfigProperties
) {

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory =
        RedisStandaloneConfiguration(moduleConfiguration.redis.host, moduleConfiguration.redis.port).apply {
            password = RedisPassword.of(moduleConfiguration.redis.password)
            username = moduleConfiguration.redis.username
        }.let { JedisConnectionFactory(it) }
}
