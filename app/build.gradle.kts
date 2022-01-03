android {
    script.StaticScript.baseExtension(this, project)
}

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

baseModuleStructure()
