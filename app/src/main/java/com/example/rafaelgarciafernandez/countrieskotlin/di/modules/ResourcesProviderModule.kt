package com.example.rafaelgarciafernandez.countrieskotlin.di.modules

import android.content.Context
import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.ResourcesProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Rafa on 09/04/2018.
 */
@Module
class ResourcesProviderModule {

    @Provides
    @Singleton
    fun provideTextProvider(context: Context): ResourcesProvider {
        return ResourcesProvider(context)
    }
}
