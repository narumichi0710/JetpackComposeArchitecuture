package com.narumichi.domain.useCase.core.di

import com.narumichi.data.repository.core.CoreRepository
import com.narumichi.domain.useCase.core.CoreUseCase
import com.narumichi.domain.useCase.core.CoreUseCaseImpl
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
    fun provideCoreUseCase(
        coreRepository: CoreRepository
    ): CoreUseCase = CoreUseCaseImpl(coreRepository)

}