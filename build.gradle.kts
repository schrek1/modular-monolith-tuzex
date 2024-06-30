plugins {
    alias(catalog.plugins.kotlin.jvm)
    alias(catalog.plugins.kotlin.kapt)
    alias(catalog.plugins.kotlin.serialization)
    alias(catalog.plugins.kotlin.allopen)
    alias(catalog.plugins.kotlin.jpa)
    alias(catalog.plugins.kotlin.noarg)
    alias(catalog.plugins.springBoot) apply false
    alias(catalog.plugins.kotlin.plugin.spring) apply false
}

allprojects {
    val catalog = rootProject.catalog

    apply(plugin = catalog.plugins.kotlin.jvm.get().pluginId)
    apply(plugin = catalog.plugins.kotlin.kapt.get().pluginId)
    apply(plugin = catalog.plugins.kotlin.serialization.get().pluginId)
    apply(plugin = catalog.plugins.kotlin.allopen.get().pluginId)
    apply(plugin = catalog.plugins.kotlin.noarg.get().pluginId)
    apply(plugin = catalog.plugins.kotlin.jpa.get().pluginId)

    group = "cz.schrek.tuzex"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }


    kotlin {
        allOpen {
            annotation("jakarta.persistence.Entity")
            annotation("jakarta.persistence.Embeddable")
            annotation("jakarta.persistence.MappedSuperclass")
        }

        noArg {
            annotation("jakarta.persistence.Entity")
            annotation("jakarta.persistence.Embeddable")
            annotation("jakarta.persistence.MappedSuperclass")
        }
    }
}

subprojects {
    val catalog = rootProject.catalog

    apply(plugin = catalog.plugins.springBoot.get().pluginId)
    apply(plugin = catalog.plugins.kotlin.plugin.spring.get().pluginId)

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    dependencies {
        kapt(catalog.spring.configProcessor)
        implementation(platform(catalog.spring.bom))
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        implementation(catalog.arrow.core)

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}



