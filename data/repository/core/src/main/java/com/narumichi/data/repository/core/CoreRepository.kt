package com.narumichi.data.repository.core

import com.narumichi.data.gateway.remote.api.ApiClient

interface CoreRepository {
}

internal class CoreRepositoryImpl(
    private val apiHelper: ApiClient
): ApiRepository(), CoreRepository {
}