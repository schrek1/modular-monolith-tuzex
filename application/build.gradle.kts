dependencies {
    implementation(project(":common"))
    implementation(project(":contracts"))

    implementation(project(":modules:customers"))
    implementation(project(":modules:cart"))
    implementation(project(":modules:discounts"))
    implementation(project(":modules:invoicing"))
    implementation(project(":modules:orders"))
    implementation(project(":modules:payment"))
    implementation(project(":modules:products"))
    implementation(project(":modules:shipping"))
}
