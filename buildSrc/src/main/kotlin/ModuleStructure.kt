import ModuleStructure.configureModuleStructure
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import script.Libs

/**
 * モジュール間の依存関係を定義するオブジェクト
 */

fun Project.baseModuleStructure() {
    configureModuleStructure(this)
}

object ModuleStructure {

    internal fun configureModuleStructure(project: Project) {
        project.run {
            val modulePath = this.path
            this.dependencies.apply {
                when (modulePath) {
                    ProjectModule.formatPath(ProjectModule.ModuleType._app) -> {
                        implAllModule(ProjectModule.ModuleType._app)
                    }
                    ProjectModule.formatPath(ProjectModule.ModuleType._viewCore) -> {
                        api(ProjectModule.ModuleType._viewModelCore)
                        api(ProjectModule.ModuleType._useCaseCore)
                    }
                    ProjectModule.formatPath(ProjectModule.ModuleType._viewModelCore) -> {
                        api(ProjectModule.ModuleType._useCaseCore)
                        api(ProjectModule.ModuleType._repositoryCore)
                    }
                    ProjectModule.formatPath(ProjectModule.ModuleType._useCaseCore) -> {
                        api(ProjectModule.ModuleType._entityCore)
                        api(ProjectModule.ModuleType._repositoryCore)
                    }
                    ProjectModule.formatPath(ProjectModule.ModuleType._repositoryCore) -> {
                        api(ProjectModule.ModuleType._gatewayRemote)
                        api(ProjectModule.ModuleType._gatewayLocal)
                    }
                    else -> {
                        //TODO: 同じレイヤー、同じドメイン内での依存伝播を実装
                    }
                }
            }
        }
    }

    private fun DependencyHandler.implAllModule(modulePath: ProjectModule.ModuleType) {
        ProjectModule.ModuleType.values().toSet()
            .minus(modulePath).forEach {
                impl(it)
            }
    }

    private fun DependencyHandler.impl(modulePath: ProjectModule.ModuleType) {
        add(Libs.Props.Impl.prop, (project(mapOf("path" to ProjectModule.formatPath(modulePath)))))
    }

    private fun DependencyHandler.api(modulePath: ProjectModule.ModuleType) {
        add(Libs.Props.Api.prop, (project(mapOf("path" to ProjectModule.formatPath(modulePath)))))
    }

}
