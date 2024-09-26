plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "hwm"
include("hwm-backend")
include("hwm-cli")
