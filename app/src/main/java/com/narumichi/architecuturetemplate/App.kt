package com.narumichi.architecuturetemplate

import android.app.Application

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