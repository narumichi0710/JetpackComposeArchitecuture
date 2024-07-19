plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.4.2")
    implementation(kotlin("gradle-plugin","1.9.25"))
    implementation("com.squareup:javapoet:1.13.0")
}