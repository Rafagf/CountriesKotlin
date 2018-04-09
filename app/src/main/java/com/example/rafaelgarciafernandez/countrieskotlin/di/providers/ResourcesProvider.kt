package com.example.rafaelgarciafernandez.countrieskotlin.di.providers

import android.content.Context
import android.support.annotation.StringRes

/**
 * Created by Rafa on 09/04/2018.
 */
class ResourcesProvider(private val context: Context) {

    fun getText(@StringRes resId: Int): String {
        return context.resources.getString(resId)
    }

    fun getText(@StringRes resId: Int, vararg args: Any): String {
        return context.resources.getString(resId, *args)
    }
}
