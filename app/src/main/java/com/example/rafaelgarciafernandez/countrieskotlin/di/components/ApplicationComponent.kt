package com.example.rafaelgarciafernandez.countrieskotlin.di.components

import android.content.Context
import android.content.SharedPreferences
import com.example.rafaelgarciafernandez.countrieskotlin.api.request.country.CountryApi
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.ApplicationModule
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.NetworkModule
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.SharedPreferencesModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Rafa on 05/04/2018.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, SharedPreferencesModule::class, NetworkModule::class))
interface ApplicationComponent {

    val context: Context

    val sharedPreferences: SharedPreferences

    val countryApi: CountryApi
}