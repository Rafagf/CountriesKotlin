package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.nhaarman.mockitokotlin2.mock
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import java.util.*

/**
 * Created by Rafa on 26/04/2018.
 */
class CountryListViewModelMapperTest {

    private val mapper = CountryListViewModelMapper()

    @Test
    fun given_country_then_view_model_fields_are_correct() {
        val country = getMockedCountry()

        val countryViewModel = mapper.mapFrom(country)

        assertEquals(countryViewModel.name, "Spain")
        assertEquals(countryViewModel.alpha2Code, "ES")
        assertEquals(countryViewModel.continent, "Europe")
        assertEquals(countryViewModel.population, "46439864")
    }

    private fun getMockedCountry(): Country {
        val country: Country = mock()
        `when`(country.name).thenReturn("Spain")
        `when`(country.alpha2Code).thenReturn("ES")
        `when`(country.alpha3Code).thenReturn("ESP")
        `when`(country.capital).thenReturn("Madrid")
        `when`(country.continent).thenReturn("Europe")
        `when`(country.region).thenReturn("Southern Europe")
        val latLng = ArrayList<Double>()
        latLng.add(40.0)
        latLng.add(-4.0)
        `when`(country.latlng).thenReturn(latLng)
        `when`(country.demonym).thenReturn("Spanish")
        `when`(country.area).thenReturn("505992.0")
        `when`(country.population).thenReturn("46439864")
        `when`(country.nativeName).thenReturn("España")
        val borders = ArrayList<String>()
        borders.add("AND")
        borders.add("FRA")
        borders.add("GIB")
        borders.add("PRT")
        borders.add("MAR")
        `when`(country.borderCountryAlphaList).thenReturn(borders)

        return country
    }
}