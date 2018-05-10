package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.Matchers.contains
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by Rafa on 10/05/2018.
 */
class DetailedCountryViewModelMapperTest {

    @Test
    fun given_country_then_view_model_fields_are_correct() {
        val viewModel = DetailedCountryViewModelMapper().mapFrom(getMockedCountry())
        assertEquals(viewModel.name, "Spain")
        assertEquals(viewModel.alpha2, "ES")
        assertEquals(viewModel.alpha3, "ESP")
        assertEquals(viewModel.capital, "Madrid")
        assertEquals(viewModel.continent, "Europe")
        assertEquals(viewModel.region, "Southern Europe")
        assertEquals(viewModel.area, 505992.0f)
        assertEquals(viewModel.population, "46439864")
        assertEquals(viewModel.demonym, "Spanish")
        assertEquals(viewModel.nativeName, "España")
        assertEquals(viewModel.latLng.latitude, 40.0)
        assertEquals(viewModel.latLng.longitude, -4.0)
        assertThat(viewModel.borderCountryAlphaList, contains<String>("AND", "FRA", "GIB", "PRT", "MAR"))
    }

    private fun getMockedCountry(): Country {
        return mock {
            on { name } doReturn "Spain"
            on { alpha2Code } doReturn "ES"
            on { alpha3Code } doReturn "ESP"
            on { capital } doReturn "Madrid"
            on { continent } doReturn "Europe"
            on { region } doReturn "Southern Europe"
            on { latLng } doReturn listOf(40.0, -4.0)
            on { demonym } doReturn "Spanish"
            on { area } doReturn 505992.0f
            on { population } doReturn "46439864"
            on { nativeName } doReturn "España"
            on { borderCountryAlphaList } doReturn listOf("AND", "FRA", "GIB", "PRT", "MAR")
        }
    }
}