package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

/**
 * Created by Rafa on 10/05/2018.
 */
interface DetailedCountryMvp {

    interface View {
        fun setFlag(url: String)
        fun setName(name: String)
        fun setContinent(continent: String)
        fun setRegion(region: String)
        fun setCapital(capital: String)
        fun setPopulation(population: String)
        fun setArea(area: String)
        fun setDemonym(demonym: String)
        fun setNativeName(nativeName: String)
        fun addMapMarker(latLng: LatLng, country: String)
        fun setBorders(borderCountries: List<String>)
        fun setBordersVisibility(visibility: Boolean)
        fun showError()
    }

    interface Interactor {
        fun getCountry(name: String): Single<Country>
        fun getBorderCountriesName(alphaCountryList: List<String>): Single<List<String>>
    }
}