package com.narumichi.domain.useCase.core

import com.narumichi.data.repository.core.CoreRepository
import javax.inject.Inject

interface GetCoreUseCase {
}

class GetUseCaseImpl @Inject constructor(
    private val coreRepository: CoreRepository
): GetCoreUseCase {
}
