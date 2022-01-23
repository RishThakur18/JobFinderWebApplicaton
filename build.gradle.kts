group = "com.jobify"
version = "0.0.1-SNAPSHOT"
description = "jobify-microservices"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    maven {
        mavenLocal()
        mavenCentral()
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("com.amazonaws:aws-java-sdk-ses:1.12.141")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.3")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test:3.4.14")

    compileOnly("org.mapstruct:mapstruct:1.4.2.Final")
    compileOnly("org.projectlombok:lombok:1.18.22")

    annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

apply(plugin = "io.spring.dependency-management")

plugins {
    java
    id("org.springframework.boot") version "2.6.2"
    id("com.palantir.docker") version "0.32.0"
}

docker {
    dependsOn(tasks.bootJar.get())
    name = "demo"
    files("build/libs/${tasks.bootJar.get().archiveFileName.get()}")
    buildArgs(mapOf("JAR_FILE" to tasks.bootJar.get().archiveFileName.get()))
    tag("dockerHub", "rishabhsingh18/jobify-backend:0.1.0")
}


