package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesLocalDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesMemoryDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesRemoteDataSource
import io.reactivex.Single

/**
 * Created by Rafa on 06/04/2018.
 */
class CountryListInteractor(private val localDataSource: CountriesLocalDataSource,
                            memoryDataSource: CountriesMemoryDataSource,
                            remoteDataSource: CountriesRemoteDataSource) : CountryListMvp.Interactor {

    override fun getCountries(): Single<List<Country>> {
        return Single.never()
    }
}