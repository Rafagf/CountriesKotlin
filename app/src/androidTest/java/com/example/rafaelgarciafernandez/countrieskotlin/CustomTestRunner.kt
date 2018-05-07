package com.example.rafaelgarciafernandez.countrieskotlin

import android.app.Application
import android.content.Context
import io.appflate.restmock.RESTMockServerStarter
import io.appflate.restmock.android.AndroidAssetsFileParser
import io.appflate.restmock.android.AndroidLogger
import io.appflate.restmock.android.RESTMockTestRunner


/**
 * Created by Rafa on 26/04/2018.
 */
class CustomTestRunner : RESTMockTestRunner() {
    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader,
                                className: String,
                                context: Context): Application {

        // I'm changing the application class only for test purposes. there I'll instantiate AppModule with RESTMock's url.
        val testApplicationClassName = TestApplication::class.java.getCanonicalName()
        return super.newApplication(cl, testApplicationClassName, context)
    }
}