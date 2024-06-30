package cz.schrek.tuzex.application

import cz.schrek.tuzex.common.Const
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [Const.BASE_PACKAGE]
)
@ConfigurationPropertiesScan(
    basePackages = [Const.BASE_PACKAGE]
)
class TuzexApplication

fun main(args: Array<String>) {
    runApplication<TuzexApplication>(*args)
}

