package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.holder

import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.FlagProvider
import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.ResourcesProvider
import com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.CountryListViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by Rafa on 26/04/2018.
 */
class CountryListViewHolderPresenterTest {

    private lateinit var view: CountryListViewHolderMvp.View
    private lateinit var resourcesProvider: ResourcesProvider
    private lateinit var flagProvider: FlagProvider
    private lateinit var presenter: CountryListViewHolderPresenter

    @Before
    fun setUp() {
        view = mock(CountryListViewHolderMvp.View::class.java)
        resourcesProvider = mock(ResourcesProvider::class.java)
        flagProvider = mock(FlagProvider::class.java)
        presenter = CountryListViewHolderPresenter(view, resourcesProvider, flagProvider)
    }

    @Test
    fun given_country_then_it_shows_flag() {
        val country = mock(CountryListViewModel::class.java)
        `when`(country.alpha2Code).thenReturn("ES")
        `when`(flagProvider.getFlagUrl("ES")).thenReturn("flag_url")

        presenter.bind(country)

        verify(flagProvider).getFlagUrl("ES")
        verify(view).setFlag("flag_url")
    }

    @Test
    fun given_country_then_it_shows_country_name() {
        val country = mock(CountryListViewModel::class.java)
        `when`(country.name).thenReturn("Spain")

        presenter.bind(country)

        verify(view).setName("Spain")
    }

    @Test
    fun given_country_with_continent_then_it_shows_continent_name() {
        val country = mock(CountryListViewModel::class.java)
        `when`(country.continent).thenReturn("Europe")

        presenter.bind(country)

        verify(view).setContinent("Europe")
    }

    @Test
    fun given_country_with_no_continent_then_it_shows_dash() {
        val country = mock(CountryListViewModel::class.java)
        `when`(country.continent).thenReturn(null)

        presenter.bind(country)

        verify(view).setContinent("-")
    }

    @Test
    fun given_country_with_population_greater_than_0_then_it_shows_population() {
        val country = mock(CountryListViewModel::class.java)
        `when`(country.population).thenReturn("500")
        `when`(resourcesProvider.getText(R.string.population)).thenReturn("Population: ")

        presenter.bind(country)

        verify(resourcesProvider).getText(R.string.population)
        verify(view).setPopulation("Population: 500")
    }

    @Test
    fun given_country_with_no_population_then_it_shows_uninhabited() {
        val country = mock(CountryListViewModel::class.java)
        `when`(country.population).thenReturn(null)
        `when`(resourcesProvider.getText(R.string.population)).thenReturn("Population: ")

        presenter.bind(country)

        verify(resourcesProvider).getText(R.string.population)
        verify(view).setPopulation("Population: -")
    }

    @Test
    fun given_country_with_population_equals_to_zero_then_it_shows_uninhabited() {
        val country = mock(CountryListViewModel::class.java)
        `when`(country.population).thenReturn("0")
        `when`(resourcesProvider.getText(R.string.population)).thenReturn("Population: ")

        presenter.bind(country)

        verify(resourcesProvider).getText(R.string.population)
        verify(view).setPopulation("Population: -")
    }
}