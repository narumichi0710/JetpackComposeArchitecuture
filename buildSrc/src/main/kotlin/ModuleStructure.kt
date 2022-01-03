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
                ModulePath.ViewCore.path -> dependencies {
                    impl(ModulePath.ViewModelCore)
                    impl(ModulePath.UseCaseCore)
                }
                ModulePath.ViewModelCore.path -> dependencies {
                    impl(ModulePath.UseCaseCore)
                    impl(ModulePath.RepositoryCore)
                }
                ModulePath.UseCaseCore.path -> dependencies {
                    impl(ModulePath.EntityCore)
                    impl(ModulePath.RepositoryCore)
                }
                ModulePath.EntityCore.path -> dependencies {
                }
                ModulePath.RepositoryCore.path -> dependencies {
                    impl(ModulePath.GatewayRemote)
                    impl(ModulePath.GatewayLocal)
                }
            }
        }
    }

    private fun DependencyHandler.impl(modulePath: ModulePath) {
        add(Libs.Props.Impl.prop, (project(mapOf("path" to modulePath.path))))
    }

    private fun DependencyHandler.api(modulePath: ModulePath) {
        add(Libs.Props.Api.prop, (project(mapOf("path" to modulePath.path))))
    }

}
