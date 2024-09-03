package com.example.hometab

import android.app.Application

class App: Application() {
    companion object{
        lateinit var preferences: sharedpreferences
    }

    override fun onCreate() {
        preferences=sharedpreferences(applicationContext)
        super.onCreate()
    }
}