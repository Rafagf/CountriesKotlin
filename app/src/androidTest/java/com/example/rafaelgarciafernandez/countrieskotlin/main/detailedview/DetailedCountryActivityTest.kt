package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.widget.TextView
import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.TestApplication
import com.example.rafaelgarciafernandez.countrieskotlin.espresso.matcherWithIndex
import com.example.rafaelgarciafernandez.countrieskotlin.getJsonFromAsset
import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.appflate.restmock.RESTMockServer
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.reflect.Type

/**
 * Created by Rafa on 29/05/2018.
 */
@RunWith(AndroidJUnit4::class)
class DetailedCountryActivityTest {

    @get:Rule
    var activityTestRule = IntentsTestRule(DetailedCountryActivity::class.java, false, false)

    @Before
    fun setUp() {
        RESTMockServer.reset()
        val countriesRepository = (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestApplication).applicationComponent.countriesRepository
        val countries = getCountriesFromJson("json/all_countries.json")
        countriesRepository.memoryDataSource.save(countries)
    }

    @Test
    fun it_shows_screen_when_launched() {
        val intent = Intent()
        intent.putExtra(DetailedCountryActivity.COUNTRY_NAME_TAG, "Spain")

        activityTestRule.launchActivity(intent)

        onView(withId(R.id.detailedViewRoot)).check(matches(isDisplayed()))
    }

    @Test
    fun given_country_then_it_shows_correct_data_() {
        val intent = Intent()
        intent.putExtra(DetailedCountryActivity.COUNTRY_NAME_TAG, "Spain")

        activityTestRule.launchActivity(intent)

        onView(allOf<View>(instanceOf<Any>(TextView::class.java), withParent(withId(R.id.toolbar)))).check(matches(withText("Spain")))
        onView(withId(R.id.capitalTextView)).check(matches(withText("Madrid")))
        onView(withId(R.id.continentTextView)).check(matches(withText("Europe")))
        onView(withId(R.id.regionTextView)).check(matches(withText("Southern Europe")))
    }

    @Test
    fun given_country_with_no_borders_then_it_shows_no_border_countries() {
        val intent = Intent()
        intent.putExtra(DetailedCountryActivity.COUNTRY_NAME_TAG, "Martinique")

        activityTestRule.launchActivity(intent)

        onView(withId(R.id.bordersLinearLayout)).check(matches(hasChildCount(0)))
    }

    @Test
    fun given_country_with_borders_then_it_shows_border_countries() {
        val intent = Intent()
        intent.putExtra(DetailedCountryActivity.COUNTRY_NAME_TAG, "Spain")

        activityTestRule.launchActivity(intent)

        onView(withId(R.id.bordersLinearLayout)).check(matches(hasChildCount(5)))
        val borderCountryName1 = onView(matcherWithIndex(withId(R.id.borderTextView), 0))
        borderCountryName1.check(matches(withText("Andorra")))
        val borderCountryName2 = onView(matcherWithIndex(withId(R.id.borderTextView), 1))
        borderCountryName2.check(matches(withText("France")))
        val borderCountryName3 = onView(matcherWithIndex(withId(R.id.borderTextView), 2))
        borderCountryName3.check(matches(withText("Gibraltar")))
        val borderCountryName4 = onView(matcherWithIndex(withId(R.id.borderTextView), 3))
        borderCountryName4.check(matches(withText("Portugal")))
        val borderCountryName5 = onView(matcherWithIndex(withId(R.id.borderTextView), 4))
        borderCountryName5.check(matches(withText("Morocco")))
    }

    @Test
    fun when_border_country_is_clicked_then_country_detailed_view_is_opened() {
        val intent = Intent()
        intent.putExtra(DetailedCountryActivity.COUNTRY_NAME_TAG, "Spain")

        activityTestRule.launchActivity(intent)

        onView(allOf<View>(instanceOf<Any>(TextView::class.java), withParent(withId(R.id.toolbar)))).check(matches(withText("Spain")))
        val borderCountryName1 = onView(matcherWithIndex(withId(R.id.borderTextView), 0))
        borderCountryName1.perform(click())
        onView(allOf<View>(instanceOf<Any>(TextView::class.java), withParent(withId(R.id.toolbar)))).check(matches(withText("Andorra")))
    }

    private fun getCountriesFromJson(path: String): List<Country> {
        val json = getJsonFromAsset(path)
        if (json != null) {
            val type: Type = Types.newParameterizedType(List::class.java, Country::class.java)
            val adapter: JsonAdapter<List<Country>> = Moshi.Builder().build().adapter(type)
            val countries: List<Country>? = adapter.fromJson(json)
            if (countries != null) {
                return countries
            }
        }

        throw IOException("Could not read countries")
    }
}