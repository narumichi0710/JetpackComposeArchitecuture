package com.narumichi.data.repository.core.di

import com.narumichi.data.gateway.remote.api.ApiClient
import com.narumichi.data.repository.core.CoreRepository
import com.narumichi.data.repository.core.CoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCoreRepository(
        apiClient: ApiClient
    ): CoreRepository = CoreRepositoryImpl(apiClient)

}