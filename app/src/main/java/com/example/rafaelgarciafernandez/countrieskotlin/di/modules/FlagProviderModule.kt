package com.example.rafaelgarciafernandez.countrieskotlin.di.modules

import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.FlagProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Rafa on 09/04/2018.
 */
@Module
class FlagProviderModule {

    @Provides
    @Singleton
    fun provideFlagProvider(): FlagProvider {
        return FlagProvider()
    }
}
