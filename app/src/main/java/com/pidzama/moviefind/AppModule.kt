package com.pidzama.moviefind

import android.app.Application
import com.pidzama.moviefind.di.MovieDataBaseModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppModule: Application() {

    override fun onCreate() {
        super.onCreate()
        MovieDataBaseModule.provideDB(applicationContext)
    }
}