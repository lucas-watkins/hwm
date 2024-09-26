plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.serialization") version "2.0.20"
    id("io.ktor.plugin") version "2.3.12"
    application
}

group = "com.lucaspowered"
version = "1.0.0"
application.mainClass = "com.lucaspowered.hwm.core.MainKt"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.ktorm:ktorm-core:4.1.1")
    implementation("mysql:mysql-connector-java:5.1.44")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}