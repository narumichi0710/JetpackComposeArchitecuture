package com.narumichi.data.repository.core

import com.narumichi.data.gateway.remote.api.ApiService

interface CoreRepository {
}

internal class CoreRepositoryImpl(
    private val apiService: ApiService
): ApiRepository(), CoreRepository {
}