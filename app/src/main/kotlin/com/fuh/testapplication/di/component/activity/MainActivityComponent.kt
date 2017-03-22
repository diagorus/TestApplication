package com.fuh.testapplication.di.component.activity

import com.fuh.testapplication.di.module.AppModule
import com.fuh.testapplication.di.module.NetworkModule
import com.fuh.testapplication.di.module.activity.MainActivityModule
import com.fuh.testapplication.di.scope.ActivityScope
import com.fuh.testapplication.ui.activity.MainActivity
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * Created by Nick on 22.03.2017.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun inject(activity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        fun loginActivityModule(module: MainActivityModule): Builder
        fun build(): MainActivityComponent
    }
}