package script

import BuildConfig
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project


object StaticScript {

    /*
    * ktsから呼び出されるgradleの設定処理を行うための関数
    */
    fun baseExtension(
        baseExtension: BaseExtension,
        project: Project? = null,
        isRoot: Boolean? = false
    ) {
        provideDefaultConfig(baseExtension, project, isRoot)
        provideBaseExtension(baseExtension)
        provideBuildSetting(baseExtension)
    }

    /*
    * 各モジュール共通のdefaultConfigを設定するための関数
    */
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

    /*
    * 各モジュールの共通ビルド設定を記述するための関数
    */
    private fun provideBaseExtension(baseExtension: BaseExtension) {
        baseExtension.apply {
            compileOptions.sourceCompatibility = JavaVersion.VERSION_1_8
            compileOptions.targetCompatibility = JavaVersion.VERSION_1_8
            lintOptions.isAbortOnError = true
            testOptions.unitTests.isIncludeAndroidResources = true
        }
    }

    /*
    * BuildType毎の設定処理を記述するための関数
    */
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

    /*
    * リリースビルドに対する設定
    */
    private fun releaseBuildSetting() {

    }

    /*
    *　デバッグビルドに対する設定
    */
    private fun debugBuildSetting() {

    }
}