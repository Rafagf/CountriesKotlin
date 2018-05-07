package com.example.rafaelgarciafernandez.countrieskotlin

import android.support.test.InstrumentationRegistry.getInstrumentation
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

/**
 * Created by Rafa on 07/05/2018.
 */
fun getJsonFromAsset(path: String): String? {
    try {
        val inputStream: InputStream = getInstrumentation().context.resources.assets.open(path)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, charset("UTF-8"))
    } catch (ex: IOException) {
        ex.printStackTrace()
    }

    return null
}