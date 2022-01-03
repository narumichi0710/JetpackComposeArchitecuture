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

tasks.create("clean", type = Delete::class) {
    delete(rootProject.buildDir)
}