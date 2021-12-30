dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "ArchitecutureTemplate"

include(":app")
include(":presentation:view:core")
include(":presentation:viewModel:core")
include(":domain:entity:core")
include(":domain:useCase:core")
include(":data:repository:core")
include(":data:gateway")
