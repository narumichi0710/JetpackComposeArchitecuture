dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "ArchitecutureTemplate"
include (":app")
include (":presentation:view")
include (":presentation:viewModel")
include (":domain:repository")
include (":domain:entity")
include (":domain:gateway")
include(":domain:repository:core")
include(":domain:entity:core")
include(":presentation:view:core")
include(":presentation:viewModel:core")
