package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesLocalDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesMemoryDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesRemoteDataSource
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Created by Rafa on 06/04/2018.
 */
class CountryListInteractor(private val localDataSource: CountriesLocalDataSource,
                            private val memoryDataSource: CountriesMemoryDataSource,
                            private val remoteDataSource: CountriesRemoteDataSource) : CountryListMvp.Interactor {

    override fun getCountries(): Single<List<Country>> {
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
}