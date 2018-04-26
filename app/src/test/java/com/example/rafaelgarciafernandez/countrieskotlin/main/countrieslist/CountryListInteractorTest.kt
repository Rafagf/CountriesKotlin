package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesLocalDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesMemoryDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesRemoteDataSource
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Created by Rafa on 26/04/2018.
 */
class CountryListInteractorTest {

    private lateinit var memoryDataSource: CountriesMemoryDataSource
    private lateinit var localDataSource: CountriesLocalDataSource
    private lateinit var remoteDataSource: CountriesRemoteDataSource
    private lateinit var interactor: CountryListInteractor

    @Before
    fun setUp() {
        memoryDataSource = mock(CountriesMemoryDataSource::class.java)
        localDataSource = mock(CountriesLocalDataSource::class.java)
        remoteDataSource = mock(CountriesRemoteDataSource::class.java)
        interactor = CountryListInteractor(localDataSource, memoryDataSource, remoteDataSource)
    }

    @Test
    fun given_memory_data_source_has_data_when_getting_countries_then_return_memory_data_countries() {
        val country = mock(Country::class.java)
        `when`(country.name).thenReturn("Spain")
        val countries = listOf(country)
        val testObserver = TestObserver<List<Country>>()
        `when`(memoryDataSource.getCountries()).thenReturn(Maybe.just(countries))
        `when`(localDataSource.getCountries()).thenReturn(Maybe.empty())
        `when`(remoteDataSource.getCountries()).thenReturn(Single.never())

        val observable = interactor.getCountries()

        observable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        val receivedCountries = testObserver.values()[0]
        assertEquals(receivedCountries[0].name, "Spain")
    }

    @Test
    fun given_local_data_source_has_data_and_memory_has_no_data_when_getting_countries_then_return_local_data_countries() {
        val country = mock(Country::class.java)
        `when`(country.name).thenReturn("Spain")
        val countries = listOf(country)
        val testObserver = TestObserver<List<Country>>()
        `when`(memoryDataSource.getCountries()).thenReturn(Maybe.empty())
        `when`(localDataSource.getCountries()).thenReturn(Maybe.just(countries))
        `when`(remoteDataSource.getCountries()).thenReturn(Single.never())

        val observable = interactor.getCountries()

        observable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        val receivedCountries = testObserver.values()[0]
        assertEquals(receivedCountries[0].name, "Spain")
    }

    @Test
    fun given_remote_data_source_has_data_and_memory_and_local_have_no_data_when_getting_countries_then_return_remote_data_countries() {
        val country = mock(Country::class.java)
        `when`(country.name).thenReturn("Spain")
        val countries = listOf(country)
        val testObserver = TestObserver<List<Country>>()
        `when`(memoryDataSource.getCountries()).thenReturn(Maybe.empty())
        `when`(localDataSource.getCountries()).thenReturn(Maybe.empty())
        `when`(remoteDataSource.getCountries()).thenReturn(Single.just(countries))

        val observable = interactor.getCountries()

        observable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        val receivedCountries = testObserver.values()[0]
        assertEquals(receivedCountries[0].name, "Spain")
    }
}