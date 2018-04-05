package com.example.rafaelgarciafernandez.countrieskotlin.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Rafa on 05/04/2018.
 */
@Module
class ApplicationModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return app.applicationContext
    }
}