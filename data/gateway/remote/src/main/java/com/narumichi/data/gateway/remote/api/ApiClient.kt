package com.narumichi.data.gateway.remote.api

import javax.inject.Inject


interface ApiClient {
}

class ApiClientImpl @Inject constructor(
    private val apiService: ApiService
) : ApiClient {
}
