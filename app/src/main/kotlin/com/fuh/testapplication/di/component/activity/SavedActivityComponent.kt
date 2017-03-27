package com.fuh.testapplication.di.component.activity

import com.fuh.testapplication.di.module.activity.SavedActivityModule
import com.fuh.testapplication.di.scope.ActivityScope
import com.fuh.testapplication.ui.activity.SavedActivity
import dagger.Subcomponent

/**
 * Created by Nick on 26.03.2017.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(SavedActivityModule::class))
interface SavedActivityComponent {
    fun inject(activity: SavedActivity)

    @Subcomponent.Builder
    interface Builder {
        fun savedActivityModule(module: SavedActivityModule): Builder
        fun build(): SavedActivityComponent
    }
}