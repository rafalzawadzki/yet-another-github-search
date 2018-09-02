package com.rafalzawadzki.github.core

import android.app.Application
import com.rafalzawadzki.github.core.di.AndroidModule
import com.rafalzawadzki.github.core.di.AppComponent
import com.rafalzawadzki.github.core.di.DaggerAppComponent

class ThisApplication : Application() {

    lateinit var applicationComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        createGraph()
    }

    fun createGraph() {
        applicationComponent = DaggerAppComponent.builder()
                .androidModule(AndroidModule(this))
                .build()
        applicationComponent.inject(this)
    }

}
