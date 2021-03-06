import Dependencies.provideDependencies
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.repositories
import script.Libs
import script.ModuleExtension

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
                    takeIf { this.hasProperty(ProjectProperty.android) }?.let { project ->
                        project.dependencies {
                            add(Libs.Props.Impl.prop, Libs.AndroidX.coreKtx)
                            add(Libs.Props.Impl.prop, Libs.AndroidX.appCompat)
                            add(Libs.Props.Impl.prop, Libs.Google.material)
                            add(Libs.Props.Impl.prop, Libs.Other.timber)
                            add(Libs.Props.Impl.prop, Libs.Coroutine.android)
                            add(Libs.Props.TestImpl.prop, Libs.Test.junit)
                            add(Libs.Props.TestImpl.prop, Libs.Test.robolectric)
                            add(Libs.Props.TestImpl.prop, Libs.Test.mockito)
                            add(Libs.Props.AndroidTestImpl.prop, Libs.Test.espresso)
                            add(Libs.Props.Impl.prop, Libs.Google.Hilt.hilt)
                            add(Libs.Props.Kapt.prop, Libs.Google.Hilt.compiler)
                            add(Libs.Props.Impl.prop, Libs.AndroidX.Compose.runtime)
                            add(Libs.Props.Impl.prop, Libs.AndroidX.Hilt.lifecycleViewModel)

                            when (project.path) {
                                ModuleExtension.convertModulePath(ProjectModule.ModuleType._app.name) -> {
                                }
                                ModuleExtension.convertModulePath(ProjectModule.ModuleType._presentation_layout_core.name) -> {
                                    add(Libs.Props.Api.prop, Libs.AndroidX.Compose.material)
                                    add(Libs.Props.Api.prop, Libs.AndroidX.Compose.ui)
                                    add(Libs.Props.Api.prop, Libs.AndroidX.Compose.preview)
                                    add(Libs.Props.Api.prop, Libs.AndroidX.Compose.tooling)
                                    add(Libs.Props.Api.prop, Libs.AndroidX.LifeCycle.runtime)
                                    add(Libs.Props.AndroidTestApi.prop, Libs.AndroidX.Compose.test)
                                }
                                ModuleExtension.convertModulePath(ProjectModule.ModuleType._presentation_view_core.name) -> {
                                    add(Libs.Props.Api.prop, Libs.AndroidX.Compose.material)
                                    add(Libs.Props.Api.prop, Libs.AndroidX.Compose.ui)
                                    add(Libs.Props.Api.prop, Libs.AndroidX.Compose.preview)
                                    add(Libs.Props.Api.prop, Libs.AndroidX.Compose.tooling)
                                    add(Libs.Props.Api.prop, Libs.AndroidX.LifeCycle.runtime)
                                    add(Libs.Props.AndroidTestApi.prop, Libs.AndroidX.Compose.test)
                                }
                                ModuleExtension.convertModulePath(ProjectModule.ModuleType._presentation_viewModel_core.name) -> {
                                    add(Libs.Props.Api.prop, Libs.AndroidX.LifeCycle.composeViewModel)
                                    add(Libs.Props.Api.prop, Libs.AndroidX.LifeCycle.viewModel)
                                    add(Libs.Props.Api.prop, Libs.AndroidX.navigationFragment)
                                }
                                ModuleExtension.convertModulePath(ProjectModule.ModuleType._domain_useCase_core.name) -> {
                                }
                                ModuleExtension.convertModulePath(ProjectModule.ModuleType._domain_entity_core.name) -> {
                                    add(Libs.Props.Api.prop, Libs.Square.Moshi.moshiKotlin)
                                }
                                ModuleExtension.convertModulePath(ProjectModule.ModuleType._data_repository_core.name) -> {
                                }
                                ModuleExtension.convertModulePath(ProjectModule.ModuleType._data_gateway_remote.name) -> {
                                    add(Libs.Props.Api.prop, Libs.Square.OkHttp.okHttp)
                                    add(Libs.Props.Api.prop, Libs.Square.OkHttp.loggingInterceptor)
                                    add(Libs.Props.Api.prop, Libs.Square.Retrofit.retrofit)
                                    add(Libs.Props.Api.prop, Libs.Square.Retrofit.converterMoshi)
                                    add(Libs.Props.Api.prop, Libs.Square.Moshi.moshiKotlin)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}