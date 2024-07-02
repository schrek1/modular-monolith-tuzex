dependencies {
    implementation(project(":contracts"))
    implementation(project(":common"))

    implementation(catalog.spring.security.crypto)

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation(catalog.postgresql)
    implementation(catalog.bundles.flyway)
}
