package com.fuh.testapplication.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Nick on 22.03.2017.
 */
@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application


    @Provides
    @Singleton
    fun provideApplication(): Application = application
}