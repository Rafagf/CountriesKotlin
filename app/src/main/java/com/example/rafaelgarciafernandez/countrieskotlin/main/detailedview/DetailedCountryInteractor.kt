package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesLocalDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesMemoryDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesRemoteDataSource
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Rafa on 25/05/2018.
 */
class DetailedCountryInteractor(private val localDataSource: CountriesLocalDataSource, private val memoryDataSource: CountriesMemoryDataSource, private val remoteDataSource: CountriesRemoteDataSource) : DetailedCountryMvp.Interactor {

    override fun getCountry(name: String): Single<Country> {
        val memorySource = memoryDataSource.getCountryByName(name)
        val localSource = localDataSource.getCountryByName(name)
        val remoteSource = remoteDataSource.getCountryByName(name)
        return Maybe.concat(memorySource, localSource, remoteSource.toMaybe()).firstElement().toSingle()
    }

    override fun getBorderCountriesName(alphaCountryList: List<String>): Single<List<String>> {
        return Observable.fromIterable(alphaCountryList)
                .flatMap { countryAlpha ->
                    val memorySource = memoryDataSource.getCountryByAlpha3(countryAlpha)
                    val localSource = localDataSource.getCountryByAlpha3(countryAlpha)
                    val remoteSource = remoteDataSource.getCountryByAlpha3(countryAlpha)

                    Maybe.concat(memorySource, localSource, remoteSource.toMaybe())
                            .firstElement()
                            .toObservable()
                            .flatMap { Observable.just(it.name) }
                }
                .toList()
    }
}