package com.fuh.testapplication

import android.app.Application
import com.fuh.testapplication.di.component.AppComponent
import com.fuh.testapplication.di.component.DaggerAppComponent
import com.fuh.testapplication.di.module.AppModule
import com.fuh.testapplication.di.module.NetworkModule
import com.fuh.testapplication.util.LocaleHelper
import io.realm.Realm
import io.realm.RealmConfiguration
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

        LocaleHelper.onCreate(this, "en")
        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule("http://api.giphy.com/"))
                .build()

        initRealm()
    }

    fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("TestApplication.realm")
                .build()
        Realm.setDefaultConfiguration(config)
    }
}