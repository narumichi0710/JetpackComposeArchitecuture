android {
    script.StaticScript.baseExtension(this)
}

plugins {
    id("com.android.library")
    kotlin("android")
}

script.ModuleStructure.baseModuleStructure(project)
