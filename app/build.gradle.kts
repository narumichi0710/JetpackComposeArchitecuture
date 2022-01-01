android {
    script.StaticScript.baseExtension(this, true, project)
}

plugins {
    id("com.android.application")
    kotlin("android")
}

script.ModuleStructure.baseModuleStructure(project)
