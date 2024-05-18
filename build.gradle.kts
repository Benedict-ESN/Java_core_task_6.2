plugins {
    id("java")
}

group = "ru.netology"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("org.hamcrest:hamcrest-all:1.3")

    }

tasks.test {
    useJUnitPlatform()
}