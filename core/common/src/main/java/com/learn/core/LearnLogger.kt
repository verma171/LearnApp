package com.learn.core

import timber.log.Timber

object LearnLogger {

    fun init() {
        Timber.plant(Timber.DebugTree())
    }

    fun d(message: String) {
        Timber.d(message)
    }

    fun d(throwable: Throwable, message: String) {
        Timber.d(throwable, message)
    }

    fun e(message: String) {
        Timber.e(message)
    }

    fun e(throwable: Throwable, message: String) {
        Timber.e(throwable, message)
    }

    fun info(message:String){
        Timber.i(message)
    }

    // Add more methods as needed for different log levels or customizations
}