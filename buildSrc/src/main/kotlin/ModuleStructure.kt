import ModuleStructure.configureModuleStructure
import ModuleStructure.findModuleType

import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import script.Libs

/**
 * モジュール間の依存関係を定義するオブジェクト
 */

fun Project.baseModuleStructure() {
    findModuleType(this)?.let {
        configureModuleStructure(this, it)
    }
}

object ModuleStructure {

    internal fun configureModuleStructure(
        project: Project,
        moduleType: ProjectModule.ModuleType?
    ) = project.dependencies.apply {
        when (moduleType) {
            ProjectModule.ModuleType._app -> {
                implAllModule(this, ProjectModule.ModuleType._app)
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
            else -> implDomainModule(this, moduleType)
        }
    }


    internal fun findModuleType(project: Project): ProjectModule.ModuleType? {
        val projectName = convertEnumName(project)
        return ProjectModule.ModuleType.values()
            .find { projectName == it.name }
    }

    internal fun implDomainModule(
        dependencyHandler: DependencyHandler,
        moduleType: ProjectModule.ModuleType?
    ) {
        sameLayerCoreModule(moduleType).forEach {
            dependencyHandler.api(it)
        }
        sameLayerChildModule(moduleType).forEach {
            dependencyHandler.api(it)
        }
    }

    private fun sameLayerCoreModule(
        moduleType: ProjectModule.ModuleType?
    ): List<ProjectModule.ModuleType> = ProjectModule.ModuleType.values().filter {
        it.name.endsWith("_core") && moduleType != it && moduleType!!.name?.startsWith(
            it?.name?.substringBefore(
                "_core"
            )
        )
    }

    private fun sameLayerChildModule(
        moduleType: ProjectModule.ModuleType?
    ): List<ProjectModule.ModuleType> = ProjectModule.ModuleType.values()
        .filter {
            moduleType != it && it.name?.startsWith(moduleType!!.name)
        }

    private fun convertEnumName(project: Project): String = project.path
        .replace(":", "_")

    private fun convertModulePath(moduleType: ProjectModule.ModuleType): String = moduleType.name
        .replace("_", ":")

    internal fun implAllModule(
        dependencyHandler: DependencyHandler,
        moduleType: ProjectModule.ModuleType
    ) = ProjectModule.ModuleType.values().toSet()
        .minus(moduleType).forEach {
            dependencyHandler.impl(it)
        }

    internal fun DependencyHandler.impl(moduleType: ProjectModule.ModuleType) {
        convertModulePath(moduleType).let { modulePath ->
            println("structure:impl => $modulePath")
            add(Libs.Props.Impl.prop, project(mapOf("path" to modulePath)))
        }
    }

    internal fun DependencyHandler.api(moduleType: ProjectModule.ModuleType) {
        convertModulePath(moduleType).let { modulePath ->
            println("structure:api => $modulePath")
            add(Libs.Props.Api.prop, project(mapOf("path" to modulePath)))
        }
    }
}
