plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
	id("jacoco")
	id("info.solidsoft.pitest") version "1.9.11"

}

group = "com.tp1"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
	testImplementation("io.kotest:kotest-assertions-core:5.9.1")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("io.kotest:kotest-property:5.9.1")
	testImplementation("io.mockk:mockk:1.13.5")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
	mainClass.set("com.tp1.TP1.Tp1ApplicationKt")
}

jacoco {
	toolVersion = "0.8.10"
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)

	reports {
		xml.required.set(true)
		html.required.set(true)
	}
}

tasks.jacocoTestCoverageVerification {
	dependsOn(tasks.jacocoTestReport)

	violationRules {
		rule {
			limit {
				minimum = "0.8".toBigDecimal()
			}
		}
	}
}
tasks.test {
	jvmArgs("-Djdk.instrument.traceUsage")
	jvmArgs("-XX:+EnableDynamicAgentLoading")
}

pitest {
	junit5PluginVersion.set("1.1.0")
	targetClasses.set(listOf("com.tp1.*"))
	targetTests.set(listOf("com.tp1.*"))
	mutationThreshold.set(80)
	outputFormats.set(listOf("HTML", "XML"))

	jvmArgs.set(listOf("--add-opens=java.base/java.lang=ALL-UNNAMED"))

	useClasspathFile.set(true)
	jvmPath.set(file(System.getenv("JAVA_HOME") + "/bin/java"))
}