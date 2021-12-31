package script

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


/**
 * ライブラリの依存関係を記述するオブジェクト
 */

object Dependencies {

    enum class DepPath(val path: String) {
        App(":app"),
        View(":presentation:view"),
        ViewModel(":presentation:viewModel"),
        UseCase(":domain:useCase"),
        Entity(":domain:entity"),
        Repository(":data:repository"),
        Gateway(":data:gateway")
    }

    fun baseDependencies(
        project: Project
    ) {
        project.subprojects {
            afterEvaluate {
                takeIf { this.hasProperty("android") }.let { project ->
                    Libraries.let {
                        project?.dependencies {
                            //TODO: AAC関連のライブラリ必要なければ削除する
                            "implementation"("androidx.core:core-ktx:1.7.0")
                            "implementation"("androidx.appcompat:appcompat:1.4.0")
                            "implementation"("com.google.android.material:material:1.4.0")
                            "implementation"("androidx.constraintlayout:constraintlayout:2.1.2")

                            // Test
                            it.provideLibs(Libraries.Props.TestImpl, Libraries.Libs.Junit)
                            it.provideLibs(Libraries.Props.AndroidTestImpl, Libraries.Libs.AndroidxJunit)
                            it.provideLibs(Libraries.Props.AndroidTestImpl, Libraries.Libs.EspressoCore)
                        }
                        when (this.path) {
                            DepPath.App.path -> dependencies {
                            }
                            DepPath.View.path -> dependencies {
                            }
                            DepPath.ViewModel.path -> dependencies {
                            }
                            DepPath.UseCase.path -> dependencies {
                            }
                            DepPath.Entity.path -> dependencies {
                            }
                            DepPath.Repository.path -> dependencies {
                            }
                            DepPath.Gateway.path -> dependencies {
                            }
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