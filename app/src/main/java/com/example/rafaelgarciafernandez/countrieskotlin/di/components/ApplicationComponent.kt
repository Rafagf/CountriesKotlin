package com.example.rafaelgarciafernandez.countrieskotlin.di.components

import android.content.Context
import android.content.SharedPreferences
import com.example.rafaelgarciafernandez.countrieskotlin.api.request.country.CountryApi
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.*
import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.FlagProvider
import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.ResourcesProvider
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Rafa on 05/04/2018.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, SharedPreferencesModule::class, NetworkModule::class, CountriesProviderModule::class, FlagProviderModule::class, ResourcesProviderModule::class))
interface ApplicationComponent {

    val context: Context

    val sharedPreferences: SharedPreferences

    val countryApi: CountryApi

    val countriesRepository: CountriesRepository

    val FlagProvider: FlagProvider

    val ResourcesProvider: ResourcesProvider
}