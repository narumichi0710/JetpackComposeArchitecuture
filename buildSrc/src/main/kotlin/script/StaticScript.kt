package script

import BuildConfig
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project


object StaticScript {

    fun baseExtension(
        baseExtension: BaseExtension,
        project: Project? = null,
        isRoot: Boolean? = false
    ) {
        provideDefaultConfig(baseExtension, project, isRoot)
        provideBaseExtension(baseExtension)
        provideBuildSetting(baseExtension)
    }

    private fun provideDefaultConfig(
        baseExtension: BaseExtension,
        project: Project?,
        isRoot: Boolean?,
    ) {
        baseExtension.apply {
            compileSdkVersion(BuildConfig.compileSdkVersion)
            defaultConfig {
                if (isRoot == true)
                    applicationId = BuildConfig.applicationId

                minSdk = BuildConfig.minSdkVersion
                targetSdk = BuildConfig.targetSdkVersion
                versionCode = BuildConfig.versionCode
                versionName = BuildConfig.versionName
                multiDexEnabled = true
                vectorDrawables.useSupportLibrary = true
                testInstrumentationRunner = BuildConfig.testInstrumentationRunner
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