import java.io.FileInputStream
import java.util.*

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
    signing
    id("com.diffplug.spotless") version "7.0.2"
}

group = "io.getstream"
version = "0.0.1"
description = "Stream official Java SDK"

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    gradlePluginPortal()
}

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api(libs.commons.math3)

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation(libs.guava)

    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.18.2")
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.apache.commons:commons-lang3:3.12.0")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    testCompileOnly("org.projectlombok:lombok:1.18.32")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.32")
}


val localProperties = Properties()
val localPropertiesFile = project.rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()

    doFirst {
        // Inject local properties into tests runtime system properties
        localProperties.forEach { k, v ->
            systemProperty(k.toString(), v.toString())
        }
    }
}

val generatedVersionDir = layout.buildDirectory.dir("generated-version")

sourceSets {
    main {
        output.dir(mapOf("builtBy" to "generateVersionProperties"), generatedVersionDir)
    }
}

spotless {
    java {
        googleJavaFormat()
    }
}

tasks.register("generateVersionProperties") {
    doLast {
        val f = layout.buildDirectory.file("generated-version/version.properties")
        val propertiesFile = f.get().asFile
        propertiesFile.parentFile.mkdirs()
        val properties = Properties()
        properties.setProperty("version", version.toString())
        propertiesFile.writer().use { properties.store(it, null) }
    }
}

tasks.named("processResources").configure {
    dependsOn("generateVersionProperties")
}

extra["ossrhUsername"] = ""
extra["ossrhPassword"] = ""
extra["signing.keyId"] = ""
extra["signing.password"] = ""
extra["signing.secretKeyRingFile"] = ""
extra["sonatypeStagingProfileId"] = ""

val secretPropsFile = project.rootProject.file("local.properties")
if (secretPropsFile.exists()) {
    // Read local.properties file first if it exists
    val properties = Properties()
    FileInputStream(secretPropsFile).use { properties.load(it) }
    properties.forEach { (name, value) -> extra[name.toString()] = value.toString() }
} else {
    // Use system environment variables
    extra["ossrhUsername"] = System.getenv("OSSRH_USERNAME") ?: ""
    extra["ossrhPassword"] = System.getenv("OSSRH_PASSWORD") ?: ""
    extra["signing.keyId"] = System.getenv("SIGNING_KEY_ID") ?: ""
    extra["signing.password"] = System.getenv("SIGNING_PASSWORD") ?: ""
    extra["signing.secretKeyRingFile"] = System.getenv("SIGNING_SECRET_KEY_RING_FILE") ?: ""
    extra["sonatypeStagingProfileId"] = System.getenv("SONATYPE_STAGING_PROFILE_ID") ?: ""
}

signing {
    sign(publishing.publications)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "stream-sdk-java"

            from(components["java"])

            pom {
                name.set("Stream official Java SDK")
                description.set("Stream Java SDK")
                url.set("https://github.com/getstream/stream-sdk-java")

                licenses {
                    license {
                        name.set("Stream License")
                        url.set("https://github.com/GetStream/stream-sdk-java/blob/main/LICENSE")
                    }
                }

                developers {
                    developer {
                        id.set("getstream-support")
                        name.set("Stream Support")
                        email.set("support@getstream.io")
                    }
                }

                scm {
                    connection.set("scm:git:github.com/getstream/stream-java-sdk.git")
                }
            }
        }
    }

    repositories {
        maven {
            name = "ossrh"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = extra["ossrhUsername"] as String? ?: ""
                password = extra["ossrhPassword"] as String? ?: ""
            }
        }
    }
}
