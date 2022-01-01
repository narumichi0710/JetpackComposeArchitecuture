package script

import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

/**
 * モジュール間の依存関係を定義するオブジェクト
 */

object ModuleStructure {

    enum class ModulePath(val path: String, val isParent: Boolean = false) {
        App(":app", true),
        View(":presentation:view", true),
        ViewCore(":presentation:view:core"),
        ViewModel(":presentation:viewModel", true),
        ViewModelCore(":presentation:viewModel:core"),
        UseCase(":domain:useCase", true),
        UseCaseCore(":domain:useCase:core"),
        Entity(":domain:entity", true),
        EntityCore(":domain:entity:core"),
        Repository(":data:repository", true),
        RepositoryCore(":data:repository:core"),
        Gateway(":data:gateway", true),
        GatewayLocal(":data:gateway:local"),
        GatewayRemote(":data:gateway:remote")
    }

    fun baseModuleStructure(project: Project) {
        project.run {
            when (this.path) {
                //親モジュール
                ModulePath.App.path -> dependencies {
                }
                ModulePath.View.path -> dependencies {
                    add(ModulePath.ViewModel)
                    add(ModulePath.App)
                    add(ModulePath.UseCase)
                }
                ModulePath.ViewModel.path -> dependencies {
                    add(ModulePath.UseCase)
                    add(ModulePath.Repository)
                }
                ModulePath.UseCase.path -> dependencies {
                    add(ModulePath.Entity)
                }
                ModulePath.Entity.path -> dependencies {
                }
                ModulePath.Repository.path -> dependencies {
                    add(ModulePath.UseCase)
                    add(ModulePath.Gateway)
                }

                //TODO: 現状の実装では子モジュールが増える度に下記に記述が増えていく様になってしまう
                // やりたいこと
                // ①子モジュールは同じファイル名の子モジュールに親モジュールと同じ様にapiで依存伝播させる
                // ②各coreモジュールからその親モジュール内の子モジュールに依存伝播させる
                //子モジュール
                ModulePath.ViewCore.path -> dependencies {
                    add(ModulePath.ViewModelCore)
                    add(ModulePath.UseCaseCore)
                }
                ModulePath.ViewModelCore.path -> dependencies {
                    add(ModulePath.UseCaseCore)
                    add(ModulePath.ViewModelCore)
                }
                ModulePath.UseCaseCore.path -> dependencies {
                    add(ModulePath.EntityCore)
                }
                ModulePath.EntityCore.path -> dependencies {
                }
                ModulePath.RepositoryCore.path -> dependencies {
                    add(ModulePath.UseCaseCore)
                    add(ModulePath.Gateway)
                }
            }
        }
    }

    //NOTE: 親モジュールはImplementation, 子モジュールはApiで依存を伝播させる
    private fun DependencyHandler.add(modulePath: ModulePath) {
        if (modulePath.isParent)
            add(Libraries.Props.Impl.prop, (project("path" to modulePath.path)))
        else
            add(Libraries.Props.Api.prop, (project("path" to modulePath.path)))
    }
}
