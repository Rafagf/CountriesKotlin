package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.holder

import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.FlagProvider
import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.ResourcesProvider
import com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.CountryListViewModel
import com.example.rafaelgarciafernandez.countrieskotlin.utils.toPopulationFormat

class CountryListViewHolderPresenter(private val view: CountryListViewHolderMvp.View, private val resourcesProvider: ResourcesProvider, private val flagProvider: FlagProvider) {

    fun bind(country: CountryListViewModel) {
        setFlag(country)
        setName(country)
        setContinent(country)
        setPopulation(country)
    }

    private fun setFlag(country: CountryListViewModel) {
        view.setFlag(flagProvider.getFlagUrl(country.alpha2Code))
    }

    private fun setName(country: CountryListViewModel) {
        view.setName(country.name)
    }

    private fun setContinent(country: CountryListViewModel) {
        if (country.continent == null || country.continent.isEmpty()) {
            view.setContinent(resourcesProvider.getText(R.string.continent) + "-")
        } else {
            view.setContinent(resourcesProvider.getText(R.string.continent) + country.continent)
        }
    }

    private fun setPopulation(country: CountryListViewModel) {
        if (country.population == null || country.population.isEmpty()) {
            view.setPopulation(resourcesProvider.getText(R.string.population) + "-")
        }
        else {
            view.setPopulation(resourcesProvider.getText(R.string.population) + country.population)
            view.setPopulation(resourcesProvider.getText(R.string.population) + country.population.toLong().toPopulationFormat())
        }
    }
}
