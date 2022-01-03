import Dependencies.provideDependencies
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.repositories
import script.Libs

/**
 * ライブラリの依存関係を記述するオブジェクト
 */


fun Project.baseDependencies() {
    provideDependencies(this)
}

object Dependencies {
    internal fun provideDependencies(project: Project) {
        project.allprojects {
            repositories {
                mavenCentral()
                google()
            }
            subprojects {
                afterEvaluate {
                    takeIf { this.hasProperty("android") }.let { project ->
                        project?.dependencies {
                            add(Libs.Props.Impl.prop, Libs.AndroidX.coreKtx)
                            add(Libs.Props.Impl.prop, Libs.AndroidX.appCompat)
                            add(Libs.Props.Impl.prop, Libs.Google.material)
                            add(Libs.Props.Impl.prop, Libs.Other.timber)
                            add(Libs.Props.TestImpl.prop, Libs.Test.junit)
                            add(Libs.Props.AndroidTestImpl.prop, Libs.Test.espresso)

                            add(Libs.Props.Impl.prop, Libs.Google.Hilt.hilt)
                            add(Libs.Props.Kapt.prop, Libs.Google.Hilt.compiler)
                            add(Libs.Props.Impl.prop, Libs.AndroidX.Hilt.lifecycleViewModel)

                            when (project.path) {
                                ModuleStructure.ModulePath.App.path -> {
                                }
                                ModuleStructure.ModulePath.View.path -> {
                                    add(Libs.Props.Impl.prop, Libs.AndroidX.Hilt.navigationCompose)
                                }
                                ModuleStructure.ModulePath.ViewModel.path -> {
                                }
                                ModuleStructure.ModulePath.UseCase.path -> {
                                }
                                ModuleStructure.ModulePath.Entity.path -> {
                                    add(Libs.Props.Impl.prop, Libs.Square.Moshi.moshi)
                                    add(Libs.Props.Impl.prop, Libs.Square.Moshi.moshiKotlin)
                                }
                                ModuleStructure.ModulePath.Repository.path -> {
                                }
                                ModuleStructure.ModulePath.Gateway.path -> {
                                    add(Libs.Props.Impl.prop, Libs.Square.OkHttp.okHttp)
                                    add(Libs.Props.Impl.prop, Libs.Square.OkHttp.loggingInterceptor)
                                    add(Libs.Props.Impl.prop, Libs.Square.Retrofit.retrofit)
                                    add(Libs.Props.Impl.prop, Libs.Square.Retrofit.converterMoshi)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}