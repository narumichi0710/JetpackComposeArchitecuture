package com.narumichi.architecuturetemplate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        saveSingleton()
    }

    private fun saveSingleton() {
        listOf<Any>(
        ).forEach {
        }
    }

    companion object {
    }
}