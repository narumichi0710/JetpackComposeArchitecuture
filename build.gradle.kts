buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath(script.Libs.Plugin.gradle)
        classpath(script.Libs.Plugin.gradlePlugin)
        classpath(script.Libs.Plugin.hiltPlugin)
    }
}
baseDependencies()


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.create("clean", type = Delete::class) {
    delete(rootProject.buildDir)
}