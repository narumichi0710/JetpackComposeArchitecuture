import ModuleStructure.configureModuleStructure
import script.ModuleExtension
import org.gradle.api.Project
import script.ModuleExtension.api
import script.ModuleExtension.impl

/**
 * モジュール間の依存関係を定義するオブジェクト
 */

fun Project.baseModuleStructure() {
    ModuleExtension.findModuleType(this)?.let {
        configureModuleStructure(this, it)
    }
}

object ModuleStructure {

    internal fun configureModuleStructure(
        project: Project,
        moduleType: ProjectModule.ModuleType
    ) = project.dependencies.apply {
        when (moduleType) {
            ProjectModule.ModuleType._app -> {
                ModuleExtension.implAllModule(this, ProjectModule.ModuleType._app)
            }
            ProjectModule.ModuleType._presentation_view_core -> {
                api(ProjectModule.ModuleType._presentation_viewModel_core)
                api(ProjectModule.ModuleType._domain_useCase_core)
            }
            ProjectModule.ModuleType._presentation_viewModel_core -> {
                api(ProjectModule.ModuleType._domain_useCase_core)
                api(ProjectModule.ModuleType._data_repository_core)
            }
            ProjectModule.ModuleType._domain_useCase_core -> {
                api(ProjectModule.ModuleType._domain_entity_core)
                api(ProjectModule.ModuleType._data_repository_core)
            }
            ProjectModule.ModuleType._data_repository_core -> {
                api(ProjectModule.ModuleType._data_gateway_remote)
                api(ProjectModule.ModuleType._data_gateway_local)
            }
            else -> ModuleExtension.implDomainModule(this, moduleType)
        }
    }

}
