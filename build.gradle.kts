group = "io.github.mikaojk"
version = "1.0.0"

val junitJupiterVersion = "6.0.0"

val javaVersion = 21

plugins {
    kotlin("jvm") version "2.2.20"
    id("application")
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("io.github.mikaojk.MainKt")
}

kotlin {
    jvmToolchain(javaVersion)
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {

    withType<Test> {
        useJUnitPlatform {}
        testLogging {
            showStandardStreams = true
            showStackTraces = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }

}
