package org.example.kotlin.multiplatform.app

import android.app.Application
import android.content.Context
import org.example.kotlin.multiplatform.di.NotDagger

class App : Application() {

    val notDagger = NotDagger()

    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            println(throwable)
            throwable.printStackTrace()
            throwable?.cause?.printStackTrace()
        }
    }
}

val Context.app
    get() = this.applicationContext as App
