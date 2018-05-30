package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesLocalDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesMemoryDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesRemoteDataSource
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`

/**
 * Created by Rafa on 27/05/2018.
 */
class DetailedCountryInteractorTest {

    private val memoryDataSource: CountriesMemoryDataSource = mock()
    private val localDataSource: CountriesLocalDataSource = mock()
    private val remoteDataSource: CountriesRemoteDataSource = mock()
    private val interactor: DetailedCountryInteractor = DetailedCountryInteractor(localDataSource, memoryDataSource, remoteDataSource)
    private val country = mock<Country> {
        on { name } doReturn "Spain"
    }

    @Test
    fun given_memory_data_source_has_data_when_getting_country_then_return_memory_data_source_content() {
        val observer = TestObserver<Country>()
        `when`(memoryDataSource.getCountryByName("Spain")).thenReturn(Maybe.just(country))
        `when`(localDataSource.getCountryByName("Spain")).thenReturn(Maybe.empty())
        `when`(remoteDataSource.getCountryByName("Spain")).thenReturn(Single.never())

        val observable = interactor.getCountry("Spain")

        observable.subscribe(observer)
        observer.assertComplete()
        observer.assertNoErrors()
        val receivedCountry = observer.values()[0]
        assertEquals(receivedCountry.name, "Spain")
    }

    @Test
    fun given_local_data_source_has_data_when_getting_country_then_return_local_data_source_content() {
        val observer = TestObserver<Country>()
        `when`(memoryDataSource.getCountryByName("Spain")).thenReturn(Maybe.empty())
        `when`(localDataSource.getCountryByName("Spain")).thenReturn(Maybe.just(country))
        `when`(remoteDataSource.getCountryByName("Spain")).thenReturn(Single.never())

        val observable = interactor.getCountry("Spain")

        observable.subscribe(observer)
        observer.assertComplete()
        observer.assertNoErrors()
        val receivedCountry = observer.values()[0]
        assertEquals(receivedCountry.name, "Spain")
    }

    @Test
    fun given_remote_data_source_has_data_when_getting_country_then_return_remote_data_source_content() {
        val observer = TestObserver<Country>()
        `when`(memoryDataSource.getCountryByName("Spain")).thenReturn(Maybe.empty())
        `when`(localDataSource.getCountryByName("Spain")).thenReturn(Maybe.empty())
        `when`(remoteDataSource.getCountryByName("Spain")).thenReturn(Single.just(country))

        val observable = interactor.getCountry("Spain")

        observable.subscribe(observer)
        observer.assertComplete()
        observer.assertNoErrors()
        val receivedCountry = observer.values()[0]
        assertEquals(receivedCountry.name, "Spain")
    }

    @Test
    fun given_memory_data_source_has_data_when_getting_border_countries_then_return_memory_data_source_content() {
        val observer = TestObserver<List<String>>()
        `when`(memoryDataSource.getCountryByAlpha3("ESP")).thenReturn(Maybe.just(country))
        `when`(localDataSource.getCountryByAlpha3("ESP")).thenReturn(Maybe.empty())
        `when`(remoteDataSource.getCountryByAlpha3("ESP")).thenReturn(Single.never())
        val borderCountryList = listOf("ESP")

        val observable = interactor.getBorderCountriesName(borderCountryList)

        observable.subscribe(observer)
        observer.assertComplete()
        observer.assertNoErrors()
        val receivedCountryList = observer.values()[0]
        assertEquals(receivedCountryList[0], "Spain")
    }

    @Test
    fun given_local_data_source_has_data_when_getting_border_countries_then_return_local_data_source_content() {
        val observer = TestObserver<List<String>>()
        `when`(memoryDataSource.getCountryByAlpha3("ESP")).thenReturn(Maybe.empty())
        `when`(localDataSource.getCountryByAlpha3("ESP")).thenReturn(Maybe.just(country))
        `when`(remoteDataSource.getCountryByAlpha3("ESP")).thenReturn(Single.never())
        val borderCountryList = listOf("ESP")

        val observable = interactor.getBorderCountriesName(borderCountryList)

        observable.subscribe(observer)
        observer.assertComplete()
        observer.assertNoErrors()
        val receivedCountryList = observer.values()[0]
        assertEquals(receivedCountryList[0], "Spain")
    }

    @Test
    fun given_remote_data_source_has_data_when_getting_border_countries_then_return_remote_data_source_content() {
        val observer = TestObserver<List<String>>()
        `when`(memoryDataSource.getCountryByAlpha3("ESP")).thenReturn(Maybe.empty())
        `when`(localDataSource.getCountryByAlpha3("ESP")).thenReturn(Maybe.empty())
        `when`(remoteDataSource.getCountryByAlpha3("ESP")).thenReturn(Single.just(country))
        val borderCountryList = listOf("ESP")

        val observable = interactor.getBorderCountriesName(borderCountryList)

        observable.subscribe(observer)
        observer.assertComplete()
        observer.assertNoErrors()
        val receivedCountryList = observer.values()[0]
        assertEquals(receivedCountryList[0], "Spain")
    }
}