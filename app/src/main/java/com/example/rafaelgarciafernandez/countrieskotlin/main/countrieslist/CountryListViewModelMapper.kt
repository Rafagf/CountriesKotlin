package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country

class CountryListViewModelMapper {

    fun mapFrom(country: Country): CountryListViewModel {
        return CountryListViewModel(country.name, country.alpha2Code, country.continent, country.population)
    }

    fun mapFrom(countryList: List<Country>): List<CountryListViewModel> {
        val countryViewModelList = mutableListOf<CountryListViewModel>()
        countryList.forEach { countryViewModelList.add(mapFrom(it)) }
        return countryViewModelList
    }
}
