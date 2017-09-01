package com.fuh.testapplication

import com.fuh.testapplication.screens.search.SearchActivityComponent
import com.fuh.testapplication.screens.saved.SavedActivityComponent
import com.fuh.testapplication.di.module.AppModule
import com.fuh.testapplication.di.module.DatabaseModule
import com.fuh.testapplication.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Nick on 22.03.2017.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class,
        DatabaseModule::class
))
interface AppComponent {
    fun mainActivityComponentBuilder(): SearchActivityComponent.Builder
    fun savedActivityComponentBuilder(): SavedActivityComponent.Builder
}