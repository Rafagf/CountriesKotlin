package com.example.rafaelgarciafernandez.countrieskotlin

import android.app.Application
import com.example.rafaelgarciafernandez.countrieskotlin.di.components.ApplicationComponent
import com.example.rafaelgarciafernandez.countrieskotlin.di.components.DaggerApplicationComponent
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.ApplicationModule
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.NetworkModule

/**
 * Created by Rafa on 05/04/2018.
 */
open class MyApplication : Application() {

    companion object {
        private const val COUNTRIES_BASE_URL = "https://restcountries.eu/rest/v1/"
    }

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        createApplicationComponent()
    }

    open fun createApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .networkModule(NetworkModule(COUNTRIES_BASE_URL))
                .build()
    }
}