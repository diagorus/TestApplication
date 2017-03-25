package com.fuh.testapplication.di.component.activity

import com.fuh.testapplication.di.module.activity.MainActivityModule
import com.fuh.testapplication.di.scope.ActivityScope
import com.fuh.testapplication.ui.activity.MainActivity
import dagger.Subcomponent

/**
 * Created by Nick on 22.03.2017.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun inject(activity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        fun mainActivityModule(module: MainActivityModule): Builder
        fun build(): MainActivityComponent
    }
}