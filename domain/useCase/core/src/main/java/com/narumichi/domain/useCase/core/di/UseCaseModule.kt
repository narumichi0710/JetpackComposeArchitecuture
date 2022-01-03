package com.narumichi.domain.useCase.core.di

import com.narumichi.data.repository.core.CoreRepository
import com.narumichi.domain.useCase.core.GetCoreUseCase
import com.narumichi.domain.useCase.core.GetUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetCoreUseCase(
        pokemonListViewRepository: CoreRepository
    ): GetCoreUseCase {
        return GetUseCaseImpl(pokemonListViewRepository)
    }

}