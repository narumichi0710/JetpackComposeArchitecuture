buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
    }
}

subprojects {
    afterEvaluate {
        if (this.hasProperty("android")) {
            dependencies {
                "implementation"("androidx.core:core-ktx:1.7.0")
                "implementation"("androidx.appcompat:appcompat:1.4.0")
                "implementation"("com.google.android.material:material:1.4.0")
                "implementation"("androidx.constraintlayout:constraintlayout:2.1.2")
                "testImplementation"("junit:junit:4.13.2")
                "androidTestImplementation"("androidx.test.ext:junit:1.1.3")
                "androidTestImplementation"("androidx.test.espresso:espresso-core:3.4.0")
            }
            when (this.path) {
                ":app" -> dependencies {
                }
                ":presentation:view" -> dependencies {
                }
                ":presentation:viewModel" -> dependencies {
                }
                ":domain:repository" -> dependencies {
                }
                ":domain:entity" -> dependencies {
                }
                ":domain:gateway" -> dependencies {
                }
            }
        }
    }
}

tasks.create("clean", type = Delete::class) {
    delete(rootProject.buildDir)
}