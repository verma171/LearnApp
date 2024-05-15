package com.learn.core

import androidx.lifecycle.viewmodel.viewModelFactory

open class CommonException : RuntimeException()
class NoInternetConnection : CommonException()
class NoContentException : CommonException()
class UnknownException:CommonException()

class Demo {
    init {

    }
}

