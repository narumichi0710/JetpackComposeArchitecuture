android {
    script.StaticScript.baseExtension(this, true, project)
}

plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    script.ModuleStructure.baseModuleStructure(project)
}