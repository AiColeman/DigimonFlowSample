package com.github.aicoleman.digimon_flow_sample.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}