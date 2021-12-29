package dependencies

import com.android.build.gradle.BaseExtension

object BuildConfig {
    const val applicationId = "com.narumichi.architecuturetemplate"
    const val versionCode = 1
    const val versionName = "1.0"
    const val targetSdkVersion = 31
    const val compileSdkVersion = 29
    const val minSdkVersion = 30
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"
}

fun BaseExtension.baseExtension() {
    defaultConfig {
        applicationId = BuildConfig.applicationId
        minSdk = BuildConfig.minSdkVersion
        targetSdk = BuildConfig.targetSdkVersion
        versionCode = BuildConfig.versionCode
        versionName = BuildConfig.versionName

        testInstrumentationRunner = BuildConfig.testInstrumentationRunner

        compileSdkVersion(BuildConfig.compileSdkVersion)
        consumerProguardFiles(BuildConfig.consumerProguardFiles)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
