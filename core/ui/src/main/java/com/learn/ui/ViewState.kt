package com.learn.ui

import com.learn.core.CommonException

sealed class ViewState<out T> {
    object Loading: ViewState<Nothing>()
    data class ContentLoaded<out T>(val response: T): ViewState<T>()
    data class LoadFailed(val e: CommonException): ViewState<Nothing>()
}