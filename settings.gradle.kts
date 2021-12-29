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