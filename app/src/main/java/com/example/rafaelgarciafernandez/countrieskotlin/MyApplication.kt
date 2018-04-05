package com.example.rafaelgarciafernandez.countrieskotlin

import android.app.Application
import com.example.rafaelgarciafernandez.countrieskotlin.di.components.ApplicationComponent
import com.example.rafaelgarciafernandez.countrieskotlin.di.components.DaggerApplicationComponent

/**
 * Created by Rafa on 05/04/2018.
 */
class MyApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        createApplicationComponent()
    }

    private fun createApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .build()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }
}