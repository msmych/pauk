plugins {
    `maven-publish`
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
}

group = "uk.matvey"
version = project.findProperty("releaseVersion") as? String ?: "0.1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    withJavadocJar()
    withSourcesJar()
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
    maven {
        name = "KitPackages"
        url = uri("https://maven.pkg.github.com/msmych/kit")
        credentials {
            username = "pauk"
            password = project.findProperty("ghPackagesRoToken") as? String ?: System.getenv("GH_PACKAGES_RO_TOKEN")
        }
    }
}

val kitVersion: String by project
val jwtVersion: String by project
val ktorVersion: String by project
val kotlinCssVersion: String by project
val junitVersion: String by project
val assertjVersion: String by project

dependencies {
    api("io.ktor:ktor-network-tls-certificates:$ktorVersion")
    api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    api("io.ktor:ktor-server-auth:$ktorVersion")
    api("io.ktor:ktor-server-call-logging:$ktorVersion")
    api("io.ktor:ktor-server-core:$ktorVersion")
    api("io.ktor:ktor-server-html-builder:$ktorVersion")
    api("io.ktor:ktor-server-netty:$ktorVersion")
    api("io.ktor:ktor-server-sse-jvm:$ktorVersion")
    api("io.ktor:ktor-server-status-pages:$ktorVersion")

    api("io.ktor:ktor-client-cio:$ktorVersion")
    api("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    api("io.ktor:ktor-client-core:$ktorVersion")
    api("io.ktor:ktor-client-logging:$ktorVersion")

    api("org.jetbrains.kotlin-wrappers:kotlin-css:$kotlinCssVersion")

    api("com.auth0:java-jwt:$jwtVersion")

    implementation("uk.matvey:kit:$kitVersion")

    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = "pauk"
            version = project.version.toString()

            from(components["java"])

            pom {
                name = "Pauk"
                description = "Kotlin web utilities"
                url = "https://github.com/msmych/pauk"

                licenses {
                    license {
                        name = "Apache-2.0"
                        url = "https://spdx.org/licenses/Apache-2.0.html"
                    }
                }
                developers {
                    developer {
                        id = "msmych"
                        name = "Matvey Smychkov"
                        email = "realsmych@gmail.com"
                    }
                }
                scm {
                    connection = "scm:git:https://github.com/msmych/pauk.git"
                    developerConnection = "scm:git:ssh://github.com/msmych/pauk.git"
                    url = "https://github.com/msmych/pauk"
                }
            }
        }
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/msmych/pauk")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GH_PACKAGES_RW_TOKEN")
                }
            }
        }
    }
}
