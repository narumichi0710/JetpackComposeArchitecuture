import dependencies.baseExtension
import dependencies.basePlugin

android {
    baseExtension(this, true, project)
}

plugins {
    basePlugin(true)
}