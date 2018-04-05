package com.example.rafaelgarciafernandez.countrieskotlin.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Rafa on 05/04/2018.
 */

@Module
class SharedPreferencesModule {

    @Provides
    @Singleton
    internal fun provideSharesPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}
