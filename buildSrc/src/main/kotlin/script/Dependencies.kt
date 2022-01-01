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
                            ModuleStructure.ModulePath.App.path -> dependencies {
                            }
                            ModuleStructure.ModulePath.View.path -> dependencies {
                            }
                            ModuleStructure.ModulePath.ViewModel.path -> dependencies {
                            }
                            ModuleStructure.ModulePath.UseCase.path -> dependencies {
                            }
                            ModuleStructure.ModulePath.Entity.path -> dependencies {
                            }
                            ModuleStructure.ModulePath.Repository.path -> dependencies {
                            }
                            ModuleStructure.ModulePath.Gateway.path -> dependencies {
                            }
                        }
                    }
                }
            }
        }
    }
}