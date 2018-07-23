package com.example.rafaelgarciafernandez.countrieskotlin.repositories

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesLocalDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesMemoryDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesRemoteDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Rafag on 23/07/2018.
 */
class CountriesRepositoryTest {

    private lateinit var memoryDataSource: CountriesMemoryDataSource
    private lateinit var localDataSource: CountriesLocalDataSource
    private lateinit var remoteDataSource: CountriesRemoteDataSource
    private lateinit var repository: CountriesRepository

    @Before
    fun setUp() {
        memoryDataSource = mock()
        localDataSource = mock()
        remoteDataSource = mock()
        repository = CountriesRepository(localDataSource, memoryDataSource, remoteDataSource)
    }

    @Test
    fun given_memory_data_source_has_data_when_getting_countries_then_return_memory_data_countries() {
        val country = mock<Country> {
            on { name } doReturn "Spain"
        }

        val countries = listOf(country)
        val testObserver = TestObserver<List<Country>>()
        Mockito.`when`(memoryDataSource.getCountries()).thenReturn(Maybe.just(countries))
        Mockito.`when`(localDataSource.getCountries()).thenReturn(Maybe.empty())
        Mockito.`when`(remoteDataSource.getCountries()).thenReturn(Single.never())

        val observable = repository.getCountries()

        observable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        val receivedCountries = testObserver.values()[0]
        TestCase.assertEquals(receivedCountries[0].name, "Spain")
    }

    @Test
    fun given_local_data_source_has_data_and_memory_has_no_data_when_getting_countries_then_return_local_data_countries() {
        val country = mock<Country> {
            on { name } doReturn "Spain"
            on { alpha2Code } doReturn "ES"
        }

        val countries = listOf(country)
        val testObserver = TestObserver<List<Country>>()
        Mockito.`when`(memoryDataSource.getCountries()).thenReturn(Maybe.empty())
        Mockito.`when`(localDataSource.getCountries()).thenReturn(Maybe.just(countries))
        Mockito.`when`(remoteDataSource.getCountries()).thenReturn(Single.never())

        val observable = repository.getCountries()

        observable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        val receivedCountries = testObserver.values()[0]
        TestCase.assertEquals(receivedCountries[0].name, "Spain")
    }

    @Test
    fun given_remote_data_source_has_data_and_memory_and_local_have_no_data_when_getting_countries_then_return_remote_data_countries() {
        val country = mock<Country> {
            on { name } doReturn "Spain"
        }

        Mockito.`when`(country.name).thenReturn("Spain")
        val countries = listOf(country)
        val testObserver = TestObserver<List<Country>>()
        Mockito.`when`(memoryDataSource.getCountries()).thenReturn(Maybe.empty())
        Mockito.`when`(localDataSource.getCountries()).thenReturn(Maybe.empty())
        Mockito.`when`(remoteDataSource.getCountries()).thenReturn(Single.just(countries))

        val observable = repository.getCountries()

        observable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        val receivedCountries = testObserver.values()[0]
        TestCase.assertEquals(receivedCountries[0].name, "Spain")
    }

    @Test
    fun given_memory_data_source_has_data_when_getting_country_by_alpha3_then_return_memory_data_source_content() {
        val country = mock<Country> {
            on { name } doReturn "Spain"
        }

        val observer = TestObserver<Country>()
        Mockito.`when`(memoryDataSource.getCountryByAlpha3("ESP")).thenReturn(Maybe.just(country))
        Mockito.`when`(localDataSource.getCountryByAlpha3("ESP")).thenReturn(Maybe.empty())
        Mockito.`when`(remoteDataSource.getCountryByAlpha3("ESP")).thenReturn(Single.never())

        val observable = repository.getCountryByAlpha3("ESP")

        observable.subscribe(observer)
        observer.assertComplete()
        observer.assertNoErrors()
        val receivedCountryList = observer.values()[0]
        Assert.assertEquals(receivedCountryList.name, "Spain")
    }

    @Test
    fun given_local_data_source_has_data_when_getting_country_by_alpha3_then_return_local_data_source_content() {
        val country = mock<Country> {
            on { name } doReturn "Spain"
        }

        val observer = TestObserver<Country>()
        Mockito.`when`(memoryDataSource.getCountryByAlpha3("ESP")).thenReturn(Maybe.empty())
        Mockito.`when`(localDataSource.getCountryByAlpha3("ESP")).thenReturn(Maybe.just(country))
        Mockito.`when`(remoteDataSource.getCountryByAlpha3("ESP")).thenReturn(Single.never())

        val observable = repository.getCountryByAlpha3("ESP")

        observable.subscribe(observer)
        observer.assertComplete()
        observer.assertNoErrors()
        val receivedCountryList = observer.values()[0]
        Assert.assertEquals(receivedCountryList.name, "Spain")
    }

    @Test
    fun given_remote_data_source_has_data_when_getting_country_by_alpha3_then_return_remote_data_source_content() {
        val country = mock<Country> {
            on { name } doReturn "Spain"
        }

        val observer = TestObserver<Country>()
        Mockito.`when`(memoryDataSource.getCountryByAlpha3("ESP")).thenReturn(Maybe.empty())
        Mockito.`when`(localDataSource.getCountryByAlpha3("ESP")).thenReturn(Maybe.empty())
        Mockito.`when`(remoteDataSource.getCountryByAlpha3("ESP")).thenReturn(Single.just(country))

        val observable = repository.getCountryByAlpha3("ESP")

        observable.subscribe(observer)
        observer.assertComplete()
        observer.assertNoErrors()
        val receivedCountryList = observer.values()[0]
        Assert.assertEquals(receivedCountryList.name, "Spain")
    }


    @Test
    fun given_memory_data_source_has_data_when_getting_country_by_name_then_return_memory_data_source_content() {
        val country = mock<Country> {
            on { name } doReturn "Spain"
            on { capital } doReturn "Madrid"
        }

        val observer = TestObserver<Country>()
        Mockito.`when`(memoryDataSource.getCountryByName("Spain")).thenReturn(Maybe.just(country))
        Mockito.`when`(localDataSource.getCountryByName("Spain")).thenReturn(Maybe.empty())
        Mockito.`when`(remoteDataSource.getCountryByName("Spain")).thenReturn(Single.never())

        val observable = repository.getCountriesByName("Spain")

        observable.subscribe(observer)
        observer.assertComplete()
        observer.assertNoErrors()
        val receivedCountryList = observer.values()[0]
        Assert.assertEquals(receivedCountryList.capital, "Madrid")
    }

    @Test
    fun given_local_data_source_has_data_when_getting_country_by_name_then_return_local_data_source_content() {
        val country = mock<Country> {
            on { name } doReturn "Spain"
            on { capital } doReturn "Madrid"
        }

        val observer = TestObserver<Country>()
        Mockito.`when`(memoryDataSource.getCountryByName("Spain")).thenReturn(Maybe.empty())
        Mockito.`when`(localDataSource.getCountryByName("Spain")).thenReturn(Maybe.just(country))
        Mockito.`when`(remoteDataSource.getCountryByName("Spain")).thenReturn(Single.never())

        val observable = repository.getCountriesByName("Spain")

        observable.subscribe(observer)
        observer.assertComplete()
        observer.assertNoErrors()
        val receivedCountryList = observer.values()[0]
        Assert.assertEquals(receivedCountryList.capital, "Madrid")
    }

    @Test
    fun given_remote_data_source_has_data_when_getting_country_by_name_then_return_remote_data_source_content() {
        val country = mock<Country> {
            on { name } doReturn "Spain"
            on { capital } doReturn "Madrid"
        }

        val observer = TestObserver<Country>()
        Mockito.`when`(memoryDataSource.getCountryByName("Spain")).thenReturn(Maybe.empty())
        Mockito.`when`(localDataSource.getCountryByName("Spain")).thenReturn(Maybe.empty())
        Mockito.`when`(remoteDataSource.getCountryByName("Spain")).thenReturn(Single.just(country))

        val observable = repository.getCountriesByName("Spain")

        observable.subscribe(observer)
        observer.assertComplete()
        observer.assertNoErrors()
        val receivedCountryList = observer.values()[0]
        Assert.assertEquals(receivedCountryList.capital, "Madrid")
    }
}