package dependencies

import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec

/**
 * NOTE:
 * rootモジュール以外での分岐が必要になった際はKotlinBuildScriptクラスから新たに関数を作成する
 */
fun PluginDependenciesSpec.basePlugin(isRoot: Boolean = false) {
    if (isRoot) {
        id("com.android.application")
    } else {
        id("com.android.library")
    }
    kotlin("android")
}
