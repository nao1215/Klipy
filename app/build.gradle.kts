plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.21"
    application
    distribution
}

repositories {
    mavenCentral()
    maven { url = uri("https://www.jitpack.io" ) }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.google.guava:guava:32.0.1-jre")
    implementation("com.github.kwhat:jnativehook:2.2.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.exposed:exposed-core:0.41.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.41.1")
    implementation("org.xerial:sqlite-jdbc:3.41.2.2")
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.5")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClass.set("com.github.nao1215.Klipy.AppKt")
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    archiveFileName.set("klipy")
    destinationDirectory.set(file("build/libs"))

    // To avoid the duplicate handling strategy error
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    // To add all of the dependencies
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

tasks {
    compileTestKotlin {
        kotlinOptions.jvmTarget = "17"
    }
}
