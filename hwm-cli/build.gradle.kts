plugins {
    kotlin("jvm")
    id("application")
}

group = "com.lucaspowered"
version = "1.0-SNAPSHOT"
application.mainClass = "com.lucaspowered.hwm.cli.MainKt"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":hwm-backend"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}