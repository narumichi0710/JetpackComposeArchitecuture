import ModuleStructure.configureModuleStructure
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import script.Libs

/**
 * モジュール間の依存関係を定義するオブジェクト
 */

fun Project.baseModuleStructure() {
    configureModuleStructure(this)
}

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

    fun configureModuleStructure(project: Project) {
        project.run {
            when (this.path) {
                //親モジュール
                ModulePath.App.path -> dependencies {
                    ModulePath.values().forEach {
                        if (it.path != ModulePath.App.path && !it.isParent)
                            impl(it)
                    }
                }
                ModulePath.View.path -> dependencies {
                    impl(ModulePath.ViewModel)
                    impl(ModulePath.App)
                    impl(ModulePath.UseCase)
                }
                ModulePath.ViewModel.path -> dependencies {
                    impl(ModulePath.UseCase)
                    impl(ModulePath.Repository)
                }
                ModulePath.UseCase.path -> dependencies {
                    impl(ModulePath.Entity)
                }
                ModulePath.Entity.path -> dependencies {
                }
                ModulePath.Repository.path -> dependencies {
                    impl(ModulePath.UseCase)
                    impl(ModulePath.Gateway)
                }

                //TODO: 現状の実装では子モジュールが増える度に下記に記述が増えていく様になってしまう
                // やりたいこと
                // ①子モジュールは同じファイル名の子モジュールに親モジュールと同じ様にapiで依存伝播させる
                // ②各coreモジュールからその親モジュール内の子モジュールに依存伝播させる
                //子モジュール
                ModulePath.ViewCore.path -> dependencies {
                    api(ModulePath.ViewModelCore)
                    api(ModulePath.UseCaseCore)
                }
                ModulePath.ViewModelCore.path -> dependencies {
                    api(ModulePath.UseCaseCore)
                }
                ModulePath.UseCaseCore.path -> dependencies {
                    api(ModulePath.EntityCore)
                }
                ModulePath.EntityCore.path -> dependencies {
                }
                ModulePath.RepositoryCore.path -> dependencies {
                    api(ModulePath.UseCaseCore)
                }
            }
        }
    }

    private fun DependencyHandler.impl(modulePath: ModulePath) {
        add(Libs.Props.Impl.prop, (project(mapOf("path" to modulePath.path))))
    }


    private fun DependencyHandler.api(modulePath: ModulePath) {
        add(Libs.Props.Api.prop, (project("path" to modulePath.path)))
    }

}
