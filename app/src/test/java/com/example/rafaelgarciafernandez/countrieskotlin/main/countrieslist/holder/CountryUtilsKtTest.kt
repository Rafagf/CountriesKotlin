package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.holder

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Rafa on 11/04/2018.
 */
class CountryUtilsKtTest {

    @Test
    fun formatPopulation() {
        var expectedResult = com.example.rafaelgarciafernandez.countrieskotlin.utils.formatPopulation("0")
        assertEquals(expectedResult, "uninhabited")

        expectedResult = com.example.rafaelgarciafernandez.countrieskotlin.utils.formatPopulation("1")
        assertEquals(expectedResult, "1")

        expectedResult = com.example.rafaelgarciafernandez.countrieskotlin.utils.formatPopulation("9999")
        assertEquals(expectedResult, "9999")

        expectedResult = com.example.rafaelgarciafernandez.countrieskotlin.utils.formatPopulation("10000")
        assertEquals(expectedResult, "10K")

        expectedResult = com.example.rafaelgarciafernandez.countrieskotlin.utils.formatPopulation("14530")
        assertEquals(expectedResult, "14.5K")

        expectedResult = com.example.rafaelgarciafernandez.countrieskotlin.utils.formatPopulation("999999")
        assertEquals(expectedResult, "999.9K")

        expectedResult = com.example.rafaelgarciafernandez.countrieskotlin.utils.formatPopulation("1000000")
        assertEquals(expectedResult, "1M")

        expectedResult = com.example.rafaelgarciafernandez.countrieskotlin.utils.formatPopulation("1423263")
        assertEquals(expectedResult, "1.4M")
    }
}