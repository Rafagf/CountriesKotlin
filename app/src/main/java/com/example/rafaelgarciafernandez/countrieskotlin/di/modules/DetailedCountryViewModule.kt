package com.example.rafaelgarciafernandez.countrieskotlin.di.modules

import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.CountriesProvider
import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.FlagProvider
import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.ResourcesProvider
import com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview.DetailedCountryInteractor
import com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview.DetailedCountryMvp
import com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview.DetailedCountryPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Rafa on 05/04/2018.
 */
@Module
class DetailedCountryViewModule(private val view: DetailedCountryMvp.View) {

    @Provides
    fun provideInteractor(countriesProvider: CountriesProvider): DetailedCountryMvp.Interactor {
        return DetailedCountryInteractor(countriesProvider.localDataSource, countriesProvider.memoryDataSource, countriesProvider.remoteDataSource)
    }

    @Provides
    fun providePresenter(interactor: DetailedCountryMvp.Interactor, resourcesProvider: ResourcesProvider, flagProvider: FlagProvider): DetailedCountryPresenter {
        return DetailedCountryPresenter(view, interactor, resourcesProvider, flagProvider)
    }
}