plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.4")
    implementation(kotlin("gradle-plugin","1.6.0"))
    implementation("com.squareup:javapoet:1.13.0")
}