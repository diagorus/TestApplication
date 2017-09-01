package com.fuh.testapplication

import android.app.Application
import com.fuh.testapplication.di.module.AppModule
import com.fuh.testapplication.di.module.NetworkModule
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber

/**
 * Created by Nick on 22.03.2017.
 */
class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
        lateinit var appName: String
    }

    override fun onCreate() {
        super.onCreate()
        appName = resources.getString(R.string.app_name)

//        LocaleHelper.onCreate(this, "en")

        initDagger()
        initRealm()
        if (BuildConfig.DEBUG) {
            initTimber()
        }
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule("http://api.giphy.com/"))
                .build()
    }

    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("$appName.realm")
                .build()
        Realm.setDefaultConfiguration(config)
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}