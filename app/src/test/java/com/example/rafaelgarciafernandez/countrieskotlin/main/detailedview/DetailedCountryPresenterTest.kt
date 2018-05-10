package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview

import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.RxImmediateSchedulerRule
import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.FlagProvider
import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.ResourcesProvider
import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.google.android.gms.maps.model.LatLng
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mockito.`when`

/**
 * Created by Rafa on 10/05/2018.
 */
class DetailedCountryPresenterTest {

    @get:Rule
    val schedulers = RxImmediateSchedulerRule()

    private val view: DetailedCountryMvp.View = mock()
    private val interactor: DetailedCountryMvp.Interactor = mock()
    private val resourcesProvider: ResourcesProvider = mock()
    private val flagProvider: FlagProvider = mock()
    private lateinit var presenter: DetailedCountryPresenter

    @Before
    fun setUp() {
        presenter = DetailedCountryPresenter(view, interactor, resourcesProvider, flagProvider)
    }

    @Test
    fun given_country_when_it_starts_then_fetch_country() {
        `when`(interactor.getCountry("Spain")).thenReturn(Single.never())

        presenter.init("Spain")

        verify(interactor).getCountry("Spain")
        verifyNoMoreInteractions(view, interactor)
    }

    @Test
    fun given_country_successfully_fetched_when_started_then_set_name() {
        val country = getMockedCountry()
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())

        presenter.init("Spain")

        verify(view).setName("Spain")
    }

    @Test
    fun given_country_successfully_fetched_when_started_then_set_flag() {
        val country = getMockedCountry()
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())
        `when`(flagProvider.getFlagUrl("ES")).thenReturn("url")

        presenter.init("Spain")

        verify(view).setFlag("url")
    }

    @Test
    fun given_country_successfully_fetched_when_started_then_set_capital() {
        val country = getMockedCountry()
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())

        presenter.init("Spain")

        verify(view).setCapital("Madrid")
    }

    @Test
    fun given_country_successfully_fetched_and_country_does_not_have_a_capital_when_started_then_set_capital_to_nothing() {
        val country = getMockedCountry()
        `when`(country.capital).thenReturn(null)
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())

        presenter.init("Spain")

        verify(view).setCapital("-")
    }

    @Test
    fun given_country_successfully_fetched_when_started_then_set_continent() {
        val country = getMockedCountry()
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())

        presenter.init("Spain")

        verify(view).setContinent("Europe")
    }

    @Test
    fun given_country_successfully_fetched_and_it_does_not_have_continent_when_started_then_set_continent_to_nothing() {
        val country = getMockedCountry()
        `when`(country.continent).thenReturn(null)
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())

        presenter.init("Spain")

        verify(view).setContinent("-")
    }

    @Test
    fun given_country_successfully_fetched_when_started_then_set_region() {
        val country = getMockedCountry()
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())

        presenter.init("Spain")

        verify(view).setRegion("Southern Europe")
    }

    @Test
    fun given_country_successfully_fetched_and_it_does_not_have_region_when_started_then_set_region_to_nothing() {
        val country = getMockedCountry()
        `when`(country.region).thenReturn(null)
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())

        presenter.init("Spain")

        verify(view).setRegion("-")
    }

    @Test
    fun given_country_successfully_fetched_when_started_then_set_population() {
        val country = getMockedCountry()
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())
        `when`(resourcesProvider.getText(R.string.population)).thenReturn("Population: ")

        presenter.init("Spain")

        verify(view).setPopulation("Population: 46.4M")
    }

    @Test
    fun given_country_successfully_fetched_and_it_does_not_have_population_when_started_then_set_population_to_nothing() {
        val country = getMockedCountry()
        `when`(country.population).thenReturn(null)
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())
        `when`(resourcesProvider.getText(R.string.population)).thenReturn("Population: ")

        presenter.init("Spain")

        verify(view).setPopulation("Population: 0")
    }

    @Test
    fun given_country_successfully_fetched_when_started_then_set_area() {
        val country = getMockedCountry()
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())
        `when`(resourcesProvider.getText(R.string.area)).thenReturn("Area: ")

        presenter.init("Spain")

        verify(view).setArea("Area: 505.9 km²")
    }

    @Test
    fun given_country_successfully_fetched_and_it_does_not_have_area_when_started_then_set_area_to_nothing() {
        val country = getMockedCountry()
        `when`(country.area).thenReturn(null)
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())
        `when`(resourcesProvider.getText(R.string.area)).thenReturn("Area: ")

        presenter.init("Spain")

        verify(view).setArea("Area: 0 m²")
    }

    @Test
    fun given_country_successfully_fetched_when_started_then_set_demonym() {
        val country = getMockedCountry()
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())
        `when`(resourcesProvider.getText(R.string.demonym)).thenReturn("Demonym: ")

        presenter.init("Spain")

        verify(view).setDemonym("Demonym: Spanish")
    }

    @Test
    fun given_country_successfully_fetched_and_it_does_not_have_demonym_when_started_then_set_demonym_to_nothing() {
        val country = getMockedCountry()
        `when`(country.demonym).thenReturn(null)
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())
        `when`(resourcesProvider.getText(R.string.demonym)).thenReturn("Demonym: ")

        presenter.init("Spain")

        verify(view).setDemonym("Demonym: -")
    }

    @Test
    fun given_country_successfully_fetched_when_started_then_set_native_name() {
        val country = getMockedCountry()
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())
        `when`(resourcesProvider.getText(R.string.native_name)).thenReturn("Native name: ")

        presenter.init("Spain")

        verify(view).setNativeName("Native name: España")
    }

    @Test
    fun given_country_successfully_fetched_and_it_does_not_have_native_name_when_started_then_set_native_name_to_nothing() {
        val country = getMockedCountry()
        `when`(country.nativeName).thenReturn(null)
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())
        `when`(resourcesProvider.getText(R.string.native_name)).thenReturn("Native name: ")

        presenter.init("Spain")

        verify(view).setNativeName("Native name: -")
    }

    @Test
    fun given_borders_successfully_fetched_when_started_then_show_borders() {
        val country = getMockedCountry()
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        val borderList = listOf("Andorra", "France", "Gibraltar", "Portugal", "Marocco")
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.just(borderList))

        presenter.init("Spain")

        verify(interactor).getBorderCountriesName(country.borderCountryAlphaList)
        verify(view).setBordersVisibility(true)
        verify(view).setBorders(borderList)
    }

    @Test
    fun given_fetching_borders_errored_when_started_then_hide_borders() {
        val country = getMockedCountry()
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.error(Throwable()))

        presenter.init("Spain")

        verify(interactor).getBorderCountriesName(country.borderCountryAlphaList)
        verify(view).setBordersVisibility(false)
    }

    @Test
    fun given_country_successfully_fetched_when_started_then_set_map() {
        val country = getMockedCountry()
        `when`(interactor.getCountry("Spain")).thenReturn(Single.just(country))
        `when`(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never())

        presenter.init("Spain")

        verify(view).addMapMarker(LatLng(40.0, -4.0), "Spain")
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
            on { area } doReturn "505992.0"
            on { population } doReturn "46439864"
            on { nativeName } doReturn "España"
            on { borderCountryAlphaList } doReturn listOf("AND", "FRA", "GIB", "PRT", "MAR")
        }
    }
}