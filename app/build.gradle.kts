android {
    script.StaticScript.baseExtension(this, project, true)
    buildFeatures.compose = true
    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true
    composeOptions.kotlinCompilerExtensionVersion = script.Libs.Versions.compose
}

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

baseModuleStructure()
