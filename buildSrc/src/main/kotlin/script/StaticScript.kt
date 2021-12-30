package script

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project


object StaticScript {

    fun baseExtension(
        baseExtension: BaseExtension,
        isRoot: Boolean = false,
        project: Project? = null,
    ) {
        provideDefaultConfig(baseExtension, isRoot)
        provideBaseExtension(baseExtension)
        provideBuildSetting(baseExtension)
    }

    private fun provideDefaultConfig(
        baseExtension: BaseExtension,
        isRoot: Boolean,
    ) {
        baseExtension.apply {
            defaultConfig {
                if (isRoot) this.applicationId = BuildConfig.applicationId
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

    private fun provideBaseExtension(baseExtension: BaseExtension) {
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

    private fun provideBuildSetting(baseExtension: BaseExtension) {
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
}