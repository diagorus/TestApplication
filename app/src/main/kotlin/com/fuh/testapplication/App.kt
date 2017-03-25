package com.fuh.testapplication

import android.app.Application
import com.fuh.testapplication.di.component.AppComponent
import com.fuh.testapplication.di.component.DaggerAppComponent
import com.fuh.testapplication.di.module.AppModule
import com.fuh.testapplication.di.module.NetworkModule
import timber.log.Timber

/**
 * Created by Nick on 22.03.2017.
 */
class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule("http://api.giphy.com/"))
                .build()
    }
}