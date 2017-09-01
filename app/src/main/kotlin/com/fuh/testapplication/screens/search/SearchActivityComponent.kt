package com.fuh.testapplication.screens.search

import com.fuh.testapplication.di.scope.ActivityScope
import dagger.Subcomponent

/**
 * Created by Nick on 22.03.2017.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(SearchActivityModule::class))
interface SearchActivityComponent {
    fun inject(activity: SearchActivity)

    @Subcomponent.Builder
    interface Builder {
        fun searchActivityModule(module: SearchActivityModule): Builder
        fun build(): SearchActivityComponent
    }
}