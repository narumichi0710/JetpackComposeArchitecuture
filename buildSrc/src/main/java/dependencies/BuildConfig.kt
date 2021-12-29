package dependencies

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

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


fun BaseExtension.baseExtension(
    baseExtension: BaseExtension,
    isRoot: Boolean = false,
    project: Project? = null,
) {
    provideDefaultConfig(baseExtension, isRoot)
    provideBaseExtension(baseExtension)
    provideBuildSetting(baseExtension)
}


fun provideDefaultConfig(
    baseExtension: BaseExtension,
    isRoot: Boolean,
) {
    baseExtension.apply {
        defaultConfig {
            if (isRoot) applicationId = BuildConfig.applicationId
            minSdk = BuildConfig.minSdkVersion
            targetSdk = BuildConfig.targetSdkVersion
            versionCode = BuildConfig.versionCode
            versionName = BuildConfig.versionName

            testInstrumentationRunner = BuildConfig.testInstrumentationRunner

            compileSdkVersion(BuildConfig.compileSdkVersion)
            consumerProguardFiles(BuildConfig.consumerProguardFiles)
        }
    }
}

fun provideBaseExtension(baseExtension: BaseExtension) {
    baseExtension.apply {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        lintOptions {
            isAbortOnError = true
        }
    }
}

fun provideBuildSetting(baseExtension: BaseExtension) {
    baseExtension.apply {
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
}
