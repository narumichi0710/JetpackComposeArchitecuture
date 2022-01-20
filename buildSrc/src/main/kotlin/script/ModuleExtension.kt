package script

import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

object ModuleExtension {

    internal fun findModuleType(project: Project): ProjectModule.ModuleType? {
        val projectName = convertEnumName(project)
        return ProjectModule.ModuleType.values()
            .find { projectName == it.name }
    }

    internal fun implDomainModule(
        dependencyHandler: DependencyHandler,
        moduleType: ProjectModule.ModuleType?
    ) {
        moduleType?.let {
            sameLayerCoreModule(it).forEach {
                dependencyHandler.api(it)
            }
            sameLayerChildModule(it).forEach {
                dependencyHandler.api(it)
            }
        }
    }

    internal fun sameLayerCoreModule(
        moduleType: ProjectModule.ModuleType
    ): List<ProjectModule.ModuleType> = ProjectModule.ModuleType.values().filter {
        it.name.endsWith("_core") && moduleType != it && moduleType.name?.startsWith(
            it?.name?.substringBefore(
                "_core"
            )
        )
    }

    internal fun sameLayerChildModule(
        moduleType: ProjectModule.ModuleType
    ): List<ProjectModule.ModuleType> = ProjectModule.ModuleType.values()
        .filter {
            moduleType != it && it.name?.startsWith(moduleType.name)
        }

    fun convertEnumName(project: Project): String = project.path
        .replace(":", "_")

    fun convertModulePath(moduleType: String): String = moduleType.replace("_", ":")

    internal fun implAllModule(
        dependencyHandler: DependencyHandler,
        moduleType: ProjectModule.ModuleType
    ) = ProjectModule.ModuleType.values().toSet()
        .minus(moduleType).forEach {
            dependencyHandler.impl(it)
        }

    internal fun DependencyHandler.impl(moduleType: ProjectModule.ModuleType) {
        convertModulePath(moduleType.name).let { modulePath ->
            println("structure:impl => $modulePath")
            add(Libs.Props.Impl.prop, project(mapOf("path" to modulePath)))
        }
    }

    internal fun DependencyHandler.api(moduleType: ProjectModule.ModuleType) {
        convertModulePath(moduleType.name).let { modulePath ->
            println("structure:api => $modulePath")
            add(Libs.Props.Api.prop, project(mapOf("path" to modulePath)))
        }
    }
}