package com.fuh.testapplication.di.module

import dagger.Module
import dagger.Provides
import io.realm.Realm

/**
 * Created by Nick on 26.03.2017.
 */

@Module
class DatabaseModule {

    @Provides
    fun provideRealm(): Realm = Realm.getDefaultInstance()
}