package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.runner.AndroidJUnit4
import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.TestApplication
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesLocalDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.CountriesMemoryDataSource
import io.appflate.restmock.RESTMockServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Rafa on 26/04/2018.
 */
@RunWith(AndroidJUnit4::class)
class CountryListActivityTest {

    private lateinit var countriesLocalDataSource: CountriesLocalDataSource
    private lateinit var countriesMemoryDataSource: CountriesMemoryDataSource

    @Rule
    var activityTestRule = IntentsTestRule(CountryListActivity::class.java, false, false)

    @Before
    fun setUp() {
        RESTMockServer.reset()
        val countriesProvider = (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestApplication).applicationComponent.countriesProvider
        countriesLocalDataSource = countriesProvider.localDataSource
        countriesMemoryDataSource = countriesProvider.memoryDataSource
        countriesLocalDataSource.clear()
        countriesMemoryDataSource.clear()
    }

    @Test
    fun it_shows_screen_when_launched() {
        activityTestRule.launchActivity(Intent())

        Espresso.onView(ViewMatchers.withId(R.id.listOfCountriesRoot)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //todo rest of tests
}