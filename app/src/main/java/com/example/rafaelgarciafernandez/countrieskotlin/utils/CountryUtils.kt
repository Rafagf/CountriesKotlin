package com.example.rafaelgarciafernandez.countrieskotlin.utils

import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Created by Rafa on 11/04/2018.
 */
fun formatPopulation(population: String): String {
    val populationNumber = Integer.valueOf(population)!!
    return when {
        populationNumber <= 0 -> "uninhabited"
        populationNumber <= 9999 -> population
        populationNumber in 10000..999999 -> {
            val decimalFormat = DecimalFormat("#.#")
            decimalFormat.roundingMode = RoundingMode.DOWN
            decimalFormat.format((populationNumber / 1000f).toDouble()) + "K"
        }
        else -> {
            val decimalFormat = DecimalFormat("#.#")
            decimalFormat.roundingMode = RoundingMode.DOWN
            decimalFormat.format((populationNumber / 1000000f).toDouble()) + "M"
        }
    }
}

fun formatArea(area: String): String {
    val areaNumber: Float = area.toFloat()
    return when {
        areaNumber < 0 -> "0 m²"
        areaNumber <= 999 -> "$area m²"
        areaNumber in 999..999999 -> {
            val decimalFormat = DecimalFormat("#.#")
            decimalFormat.roundingMode = RoundingMode.DOWN
            decimalFormat.format((areaNumber / 1000f).toDouble()) + " km²"
        }
        else -> {
            val decimalFormat = DecimalFormat("#.#")
            decimalFormat.roundingMode = RoundingMode.DOWN
            decimalFormat.format((areaNumber / 1000000f).toDouble()) + "M km²"
        }
    }
}