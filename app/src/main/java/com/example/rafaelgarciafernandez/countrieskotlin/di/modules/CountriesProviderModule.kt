package com.example.rafaelgarciafernandez.countrieskotlin.di.modules

import android.content.SharedPreferences
import com.example.rafaelgarciafernandez.countrieskotlin.api.request.country.CountryApi
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesLocalDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesMemoryDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesRemoteDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Rafa on 08/04/2018.
 */
@Module
class CountriesProviderModule {

    @Provides
    @Singleton
    internal fun provideCountriesProvider(countryApi: CountryApi, sharedPreferences: SharedPreferences): CountriesRepository {
        return CountriesRepository(
                CountriesLocalDataSource(sharedPreferences),
                CountriesMemoryDataSource(),
                CountriesRemoteDataSource(countryApi))
    }
}
