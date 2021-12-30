package script

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


/**
 * ライブラリの依存関係を記述するオブジェクト
 */

object Dependencies {

    fun baseDependencies(
        project: Project
    ) {
        project.subprojects {
            afterEvaluate {
                takeIf { this.hasProperty("android") }
                    .let {
                        it?.dependencies {
                            Libraries.let { it ->
                                //TODO: AAC関連のライブラリ必要なければ削除する
                                "implementation"("androidx.core:core-ktx:1.7.0")
                                "implementation"("androidx.appcompat:appcompat:1.4.0")
                                "implementation"("com.google.android.material:material:1.4.0")
                                "implementation"("androidx.constraintlayout:constraintlayout:2.1.2")

                                // Test
                                it.provideImpl(it.testImpl, it.junit)
                                it.provideImpl(it.androidTestImpl,it.androidxJunit)
                                it.provideImpl(it.androidTestImpl, it.espressoCore)
                            }
                        }
                        when (this.path) {
                            ":app" -> dependencies {

                            }
                            ":presentation:view" -> dependencies {
                            }
                            ":presentation:viewModel" -> dependencies {
                            }
                            ":domain:useCase" -> dependencies {
                            }
                            ":domain:entity" -> dependencies {
                            }
                            ":data:repository" -> dependencies {
                            }
                            ":data:gateway" -> dependencies {
                            }
                        }
                    }
            }
        }
    }
}

/**
 * NOTE:
 * 全モジュールで利用するライブラリを定義する関数
 * 現状はプロジェクト直下のktsで定義しているが、今後使うかもしれないのでコメントアウトしておく
 */

//fun Project.baseDependencies(additionalConfiguration: DependencyHandlerScope.() -> Unit) {
//    dependencies {
//        implementation(Libs.Kotlin.stdlib)
//        implementation(Libs.AndroidX.appCompat)
//        implementation(Libs.AndroidX.coreKtx)
//        implementation(Libs.AndroidX.constraint)
//    }
//    dependencies(additionalConfiguration)
//}
//
//private fun DependencyHandler.implementation(depName: Any) {
//    add("implementation", depName)
//}