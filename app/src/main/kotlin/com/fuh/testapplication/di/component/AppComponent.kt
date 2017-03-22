package com.fuh.testapplication.di.component

import com.fuh.testapplication.di.component.activity.MainActivityComponent
import com.fuh.testapplication.di.module.AppModule
import com.fuh.testapplication.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Nick on 22.03.2017.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class
))
interface AppComponent {
    fun mainActivityComponentBuilder(): MainActivityComponent.Builder
}