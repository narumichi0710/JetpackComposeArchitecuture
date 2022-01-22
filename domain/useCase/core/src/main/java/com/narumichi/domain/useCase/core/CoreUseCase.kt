package com.narumichi.domain.useCase.core

import com.narumichi.data.repository.core.CoreRepository
import javax.inject.Inject

interface CoreUseCase {
}

class CoreUseCaseImpl @Inject constructor(
    private val coreRepository: CoreRepository
): CoreUseCase {
}