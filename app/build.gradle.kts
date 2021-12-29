import dependencies.baseExtension

android {
    baseExtension(this, true, project)
}


plugins {
    id("com.android.library")
    kotlin("android")
}
