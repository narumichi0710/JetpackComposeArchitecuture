package script

import ProjectProperty
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BuildType
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.NamedDomainObjectContainer
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
        provideDefaultConfig(isRoot, project, baseExtension)
        provideBaseExtension(baseExtension)
        provideBuildSetting(isRoot, baseExtension)
    }

    /*
    * 各モジュール共通のdefaultConfigを設定するための関数
    */
    private fun provideDefaultConfig(
        isRoot: Boolean?,
        project: Project?,
        baseExtension: BaseExtension,
    ) {
        baseExtension.apply {
            compileSdkVersion(ProjectProperty.compileSdkVersion)
            defaultConfig {
                if (isRoot == true)
                    applicationId = ProjectProperty.applicationId

                minSdk = ProjectProperty.minSdkVersion
                targetSdk = ProjectProperty.targetSdkVersion
                versionCode = ProjectProperty.versionCode
                versionName = ProjectProperty.versionName
                multiDexEnabled = true
                vectorDrawables.useSupportLibrary = true
                testInstrumentationRunner = ProjectProperty.testInstrumentationRunner
                consumerProguardFiles(ProjectProperty.consumerProguardFiles)
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
    private fun provideBuildSetting(
        isRoot: Boolean?,
        baseExtension: BaseExtension
    ) = baseExtension.apply {
        flavorDimensions(ProjectProperty.environment)
        productFlavors {
            ProjectProperty.FlavorType.values().forEach { flavorType ->
                create(flavorType.name) {
                    if (isRoot == true && flavorType != ProjectProperty.FlavorType.prod) {
                        applicationIdSuffix = flavorType.name
                    }
                    buildTypes {
                        ProjectProperty.ProjectBuildType.values().forEach { projectBuildType ->
                            executeBuildType(isRoot, baseExtension, this, flavorType, projectBuildType)

                        }
                    }
                }
            }

        }
    }

    /*
    * BuildTypeに応じて設定処理を呼び出すための関数
    */
    private fun executeBuildType(
        isRoot: Boolean?,
        baseExtension: BaseExtension,
        objectContainer: NamedDomainObjectContainer<BuildType>,
        flavorType: ProjectProperty.FlavorType,
        projectBuildType: ProjectProperty.ProjectBuildType
    ) = objectContainer.apply {
        Action<BuildType> {
            projectBuildType.action(this, flavorType)
            if (projectBuildType == ProjectProperty.ProjectBuildType.release) {
                releaseBuildSetting(isRoot, baseExtension, this)
            } else {
                debugBuildSetting(baseExtension, this)
            }
        }.let {
            if (projectBuildType in defaultExistBuildType) getByName(projectBuildType.name, it)
            else runCatching { create(projectBuildType.name, it) }
                .getOrElse { _ -> getByName(projectBuildType.name, it) }
        }
    }

    /**
     * リリースビルドに対する設定
     */
    private fun releaseBuildSetting(
        isRoot: Boolean?,
        baseExtension: BaseExtension,
        buildType: BuildType
    ) = buildType.apply {
        if (isRoot == true) {
            isShrinkResources = true
        }
        isMinifyEnabled = true
        proguardFiles(
            baseExtension.getDefaultProguardFile(ProjectProperty.proguardAndroid),
            ProjectProperty.proguardRules
        )
    }

    /**
     * デバッグビルドに対する設定
     */
    private fun debugBuildSetting(
        baseExtension: BaseExtension,
        buildType: BuildType
    ) = buildType.apply {
        signingConfig = baseExtension.signingConfigs.getByName(ProjectProperty.ProjectBuildType.debug.name)
        setDebuggable(true)
        baseExtension.splits {
            abi.isEnable = false
            density.isEnable = false
        }
    }

    /**
     * gradle.ktsのBuildTypeの仕様として
     * すでに存在するもの(release, debug)は取得、
     * 自分で新たに作ったものは新規作成となっているので
     * その条件分岐を判定するためのリスト
     */
    private val defaultExistBuildType = listOf(
        ProjectProperty.ProjectBuildType.release,
        ProjectProperty.ProjectBuildType.debug
    )
}