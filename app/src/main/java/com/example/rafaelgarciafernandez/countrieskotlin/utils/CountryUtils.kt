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

//TODO Test this and do something similar for population
private val areaFormat = DecimalFormat("#.#").apply {
    roundingMode = RoundingMode.DOWN
}

fun Float.toMeters() = "$this m²"
fun Float.toKiloMeters() = "${areaFormat.format(this / 1_000f)} km²"
fun Float.toMegaMeters() = "${areaFormat.format(this / 1_000_000f)}M km²"

fun Float.toAreaFormat(): String = when (this) {
    in Float.MIN_VALUE..0f -> "0 m²"
    in 0f..1000f -> toMeters()
    in 1000f..1_000_000f -> toKiloMeters()
    else -> toMegaMeters()
}