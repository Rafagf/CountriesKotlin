package com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Created by Rafag on 23/07/2018.
 */
class CountriesRepository(val localDataSource: CountriesLocalDataSource,
                          val memoryDataSource: CountriesMemoryDataSource,
                          val remoteDataSource: CountriesRemoteDataSource) {

    fun getCountries(): Single<List<Country>> {
        val memorySource = memoryDataSource.getCountries()
        val localSource = localDataSource.getCountries()
                .doOnSuccess { memoryDataSource.save(it) }
        val remoteSource = remoteDataSource.getCountries()
                .doOnSuccess({
                    memoryDataSource.save(it)
                    localDataSource.save(it)
                })

        return Maybe.concat(memorySource, localSource, remoteSource.toMaybe()).firstElement().toSingle()
    }

    fun getCountriesByName(name: String): Single<Country> {
        val memorySource = memoryDataSource.getCountryByName(name)
        val localSource = localDataSource.getCountryByName(name)
        val remoteSource = remoteDataSource.getCountryByName(name)
        return Maybe.concat(memorySource, localSource, remoteSource.toMaybe()).firstElement().toSingle()

    }

    fun getCountryByAlpha3(alpha3: String): Single<Country> {
        val memorySource = memoryDataSource.getCountryByAlpha3(alpha3)
        val localSource = localDataSource.getCountryByAlpha3(alpha3)
        val remoteSource = remoteDataSource.getCountryByAlpha3(alpha3)
        return Maybe.concat(memorySource, localSource, remoteSource.toMaybe()).firstElement().toSingle()
    }
}