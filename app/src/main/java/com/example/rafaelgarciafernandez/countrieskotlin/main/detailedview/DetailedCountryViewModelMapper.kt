package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.google.android.gms.maps.model.LatLng

class DetailedCountryViewModelMapper {

    fun mapFrom(country: Country): DetailedCountryViewModel {
        val latLng = LatLng(country.latlng[0], country.latlng[1])

        return DetailedCountryViewModel(country.name, country.alpha2Code, country.alpha3Code, country.capital,
                country.continent, country.region, country.area, country.nativeName, country.population,
                country.demonym, latLng, country.borderCountryAlphaList)
    }

    fun mapFrom(countryList: List<Country>): List<DetailedCountryViewModel> {
        val countryViewModelList = mutableListOf<DetailedCountryViewModel>()
        countryList.forEach { countryViewModelList.add(mapFrom(it)) }
        return countryViewModelList
    }
}
