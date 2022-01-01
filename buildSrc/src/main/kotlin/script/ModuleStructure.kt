package script

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * モジュール間の依存関係を定義するオブジェクト
 */

object ModuleStructure {
    fun baseModuleStructure(project: Project) {
        project.run {
            when (this.path) {
                Dependencies.DepPath.App.path -> dependencies {
                }
                Dependencies.DepPath.View.path -> dependencies {
                }
                Dependencies.DepPath.ViewModel.path -> dependencies {
                }
                Dependencies.DepPath.UseCase.path -> dependencies {
                }
                Dependencies.DepPath.Entity.path -> dependencies {
                }
                Dependencies.DepPath.Repository.path -> dependencies {
                }
                Dependencies.DepPath.Gateway.path -> dependencies {
                }
            }
        }
    }
}
