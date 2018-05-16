package com.example.rafaelgarciafernandez.countrieskotlin.di.providers

import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesLocalDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesMemoryDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesRemoteDataSource

/**
 * Created by Rafa on 06/04/2018.
 */
data class CountriesProvider(val localDataSource: CountriesLocalDataSource, val memoryDataSource: CountriesMemoryDataSource, val remoteDataSource: CountriesRemoteDataSource)

