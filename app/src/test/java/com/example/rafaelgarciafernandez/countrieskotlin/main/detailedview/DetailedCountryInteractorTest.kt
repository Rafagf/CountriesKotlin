package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import junit.framework.TestCase
import org.junit.Test
import org.mockito.Mockito.`when`

/**
 * Created by Rafa on 27/05/2018.
 */
class DetailedCountryInteractorTest {

    private val countriesRepository: CountriesRepository = mock()
    private val interactor: DetailedCountryInteractor = DetailedCountryInteractor(countriesRepository)

    @Test
    fun given_list_of_alpha3_countries_when_getting_countries_names_then_return_names() {
        val alphaList = listOf("ESP", "POR")
        val spain = mock<Country> {
            on { name } doReturn "Spain"
            on { alpha3Code } doReturn "ESP"
        }
        val portugal = mock<Country> {
            on { name } doReturn "Portugal"
            on { alpha3Code } doReturn "POR"
        }
        `when`(countriesRepository.getCountryByAlpha3("ESP")).thenReturn(Single.just(spain))
        `when`(countriesRepository.getCountryByAlpha3("POR")).thenReturn(Single.just(portugal))
        val testObserver = TestObserver<List<String>>()

        val observable = interactor.getBorderCountriesName(alphaList)
        observable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        TestCase.assertEquals(testObserver.values()[0][0], "Spain")
        TestCase.assertEquals(testObserver.values()[0][1], "Portugal")
    }
}