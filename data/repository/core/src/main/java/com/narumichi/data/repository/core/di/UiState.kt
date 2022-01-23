package com.narumichi.data.repository.core.di

data class UiState<T>(
    val data: T? = null,
    val loading: Boolean = false,
    val exception: Exception? = null,
) {
    /**
     * エラーかどうか
     */
    val hasError: Boolean
        get() = exception != null

    /**
     * 初回読み込み中かどうか
     */
    val initialLoad: Boolean
        get() = data == null && loading && !hasError

    fun <N> convert(newTypeData: N): UiState<N> = UiState(
        data = newTypeData,
        loading = loading,
        exception = exception
    )
}
