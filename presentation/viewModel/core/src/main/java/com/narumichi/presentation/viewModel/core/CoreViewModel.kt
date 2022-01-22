package com.narumichi.presentation.viewModel.core

import androidx.lifecycle.ViewModel
import com.narumichi.domain.useCase.core.CoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

fun fakeCoreViewModel() : CoreViewModel {
    val coreUseCase = object : CoreUseCase {
    }

    return CoreViewModel(coreUseCase)
}


@HiltViewModel
class CoreViewModel @Inject constructor(
    private val coreUseCase: CoreUseCase
): ViewModel() {
}