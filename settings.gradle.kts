plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "modular-tuzex"

include("application")
include("modules:customers")
include("modules:orders")
include("modules:discounts")
include("contracts")
include("modules:shipping")
include("modules:invoicing")
include("modules:payment")
include("modules:cart")
include("modules:products")
include("common")


dependencyResolutionManagement {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("catalog") {
            from(files("$rootDir/gradle/libs.versions.toml"))
        }
    }
}
include("common")
