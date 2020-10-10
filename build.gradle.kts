
plugins {
    kotlin("jvm") version "1.3.61"
}

group = "com.londogard"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("no.tornado:tornadofx:1.7.17")
    implementation("com.1stleg:jnativehook:2.1.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}