android {
    script.StaticScript.baseExtension(this)
}

plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {
    script.ModuleStructure.baseModuleStructure(project)
}