object ProjectModule {

    /**
     * モジュールのレイヤー一覧
     */
    enum class LayerType {
        view,
        viewModel,
        useCase,
        entity,
        repository,
        gateway,
    }

    /**
     * ドメイン一覧
     */
    enum class DomainType {
        core
    }

    enum class ModuleType(
        val layerType: LayerType? = null,
        val domainType: DomainType? = null,
    ) {
        _app(),
        _presentation_view_core(LayerType.view, DomainType.core),
        _presentation_viewModel_core(LayerType.viewModel, DomainType.core),
        _domain_useCase_core(LayerType.useCase, DomainType.core),
        _domain_entity_core(LayerType.entity, DomainType.core),
        _data_repository_core(LayerType.repository, DomainType.core),
        _data_gateway_local(LayerType.gateway),
        _data_gateway_remote(LayerType.gateway),
    }

}