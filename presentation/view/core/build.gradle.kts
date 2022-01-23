android {
    script.StaticScript.baseExtension(this)
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = script.Libs.Versions.compose
}

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

baseModuleStructure()
