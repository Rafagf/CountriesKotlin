package com.example.rafaelgarciafernandez.countrieskotlin.utils

import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Created by Rafa on 11/04/2018.
 */
fun Long.toPopulationFormat(): String {
    return when {
        this <= 0 -> "uninhabited"
        this <= 9999 -> this.toString()
        this in 10000..999999 -> {
            val decimalFormat = DecimalFormat("#.#")
            decimalFormat.roundingMode = RoundingMode.DOWN
            decimalFormat.format((this / 1000f)) + "K"
        }
        else -> {
            val decimalFormat = DecimalFormat("#.#")
            decimalFormat.roundingMode = RoundingMode.DOWN
            decimalFormat.format((this / 1000000f)) + "M"
        }
    }
}

private val areaFormat = DecimalFormat("#.#").apply {
    roundingMode = RoundingMode.DOWN
}

fun Float.toMeters() = "$this m²"
fun Float.toKiloMeters() = "${areaFormat.format(this / 1_000f)} km²"
fun Float.toMegaMeters() = "${areaFormat.format(this / 1_000_000f)}M km²"

fun Float?.toAreaFormat(): String {
    return when {
        this == null || this <= 0f -> "0 m²"
        this < 1000f -> toMeters()
        this < 1_000_000 -> toKiloMeters()
        else -> toMegaMeters()
    }
}