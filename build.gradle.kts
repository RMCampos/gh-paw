plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.23"
    id("org.graalvm.buildtools.native") version "0.10.1"
    application
}

group = "com.github.ricardo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // CLI Parsing
    implementation("com.github.ajalt.clikt:clikt:4.2.2")
    
    // Terminal Styling
    implementation("com.github.ajalt.mordant:mordant:2.3.0")

    // HTTP Client (Ktor) - Using CIO engine for better GraalVM compatibility
    val ktorVersion = "2.3.12"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")

    // Logging Implementation
    implementation("org.slf4j:slf4j-simple:2.0.13")

    testImplementation(kotlin("test"))
}

application {
    mainClass.set("com.github.ricardo.MainKt")
}

kotlin {
    jvmToolchain(21)
}

graalvmNative {
    binaries {
        named("main") {
            imageName.set("gh-paw")
            mainClass.set("com.github.ricardo.MainKt")
            buildArgs.add("--no-fallback")
        }
    }
    metadataRepository {
        enabled.set(true)
    }
}
