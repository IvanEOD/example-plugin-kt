plugins {
    java
    kotlin("jvm")
}

repositories {
    mavenLocal()
    maven("https://repo.runelite.net")
    mavenCentral()
}



fun property(key: String): String = extra[key] as String
fun version(key: String): String = property("$key.version")

group = property("dev.group")
version = version("dev")

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    val runeliteVersion = version("runelite")
    val lombokVersion = "1.18.30"
    compileOnly("net.runelite:client:$runeliteVersion")
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    
    testImplementation(kotlin("test-junit"))
    testImplementation("junit:junit:4.12")
    testImplementation("net.runelite:client:$runeliteVersion")
    testImplementation("net.runelite:jshell:$runeliteVersion")
}

kotlin {
    jvmToolchain(11)
    compilerOptions {
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

tasks {
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(11)
    }
    
    test {
        useJUnitPlatform()
    }
    
    register<Jar>("shadowJar") {
        dependsOn(configurations.testRuntimeClasspath)
        manifest {
            attributes(
                "Main-Class" to "com.example.ExampleKotlinPluginTest",
                "Multi-Release" to "true"
            )
        }
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from(sourceSets.main.get().output)
        from(sourceSets.test.get().output)
        from(configurations.testRuntimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
        exclude("META-INF/INDEX.LIST", "META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "**/module-info.class")
        group = BasePlugin.BUILD_GROUP
        archiveClassifier.set("shadow")
        archiveFileName.set("${rootProject.name}-${project.version}-all.jar")
    }
    
}

