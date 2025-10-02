group = "io.github.mikaojk"
version = "0.0.1"

val junitJupiterVersion = "5.12.1"

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
    jvmToolchain(21)
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
