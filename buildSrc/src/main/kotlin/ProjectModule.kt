object ProjectModule {

    /**
     * モジュールのレイヤー一覧
     */
    enum class Layer {
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
    enum class Domain {
        core
    }

    enum class ModuleType(
        val layerType: Layer? = null,
        val domainType: Domain? = null,
    ) {
        _app(),
        _presentation_view_core(Layer.view, Domain.core),
        _presentation_viewModel_core(Layer.viewModel, Domain.core),
        _domain_useCase_core(Layer.useCase, Domain.core),
        _domain_entity_core(Layer.entity, Domain.core),
        _data_repository_core(Layer.repository, Domain.core),
        _data_gateway_local(Layer.gateway),
        _data_gateway_remote(Layer.gateway),
    }

    enum class LayerType(
        val layerType: Layer? = null,
    ) {
        _presentation_view(Layer.view),
        _presentation_viewModel(Layer.viewModel),
        _domain_useCase(Layer.useCase),
        _domain_entity(Layer.entity),
        _data_repository(Layer.repository),
    }
}