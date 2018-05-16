package com.example.rafaelgarciafernandez.countrieskotlin.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat.getColor
import android.widget.Toast

/**
 * Created by Rafa on 11/04/2018.
 */
fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun Activity.setStatusBarColor(@ColorRes color: Int) {
    this.window.statusBarColor = getColor (this, color)
}

inline fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.func()
    editor.apply()
}