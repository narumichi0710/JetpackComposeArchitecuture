object ProjectModule {

    enum class LayerType(val path: String, val isParent: Boolean = true) {
        App(":app", true),
        View(":presentation:view"),
        ViewModel(":presentation:viewModel"),
        UseCase(":domain:useCase"),
        Entity(":domain:entity"),
        Repository(":data:repository"),
        Gateway(":data:gateway"),
    }

    enum class DomainType(val path: String) {
        Core(":core")
    }

    enum class OptionalDomainType(val path: String) {
        GatewayLocal(":local"),
        GatewayRemote(":remote")
    }

    enum class ModuleType(
        val layerType: LayerType,
        val domainType: DomainType? = null,
        val optionalDomainType: OptionalDomainType? = null,
    ) {
        _app(LayerType.App),
        _view(LayerType.View),
        _viewCore(LayerType.View, DomainType.Core),
        _viewModel(LayerType.ViewModel),
        _viewModelCore(LayerType.ViewModel, DomainType.Core),
        _useCase(LayerType.UseCase),
        _useCaseCore(LayerType.UseCase, DomainType.Core),
        _entity(LayerType.Entity),
        _entityCore(LayerType.Entity, DomainType.Core),
        _repository(LayerType.Repository),
        _repositoryCore(LayerType.Repository, DomainType.Core),
        _gateway(LayerType.Gateway),
        _gatewayLocal(LayerType.Gateway, optionalDomainType = OptionalDomainType.GatewayLocal),
        _gatewayRemote(LayerType.Gateway, optionalDomainType = OptionalDomainType.GatewayRemote),
    }

    fun formatPath(modulePath: ProjectModule.ModuleType) =
        modulePath.layerType.path + modulePath.domainType?.path + modulePath.optionalDomainType?.path
}