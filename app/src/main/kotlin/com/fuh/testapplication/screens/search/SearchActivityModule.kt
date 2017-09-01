package com.fuh.testapplication.screens.search

import com.fuh.testapplication.screens.search.SearchContract
import com.fuh.testapplication.di.scope.ActivityScope
import com.fuh.testapplication.screens.search.SearchPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Nick on 22.03.2017.
 */
@Module
class SearchActivityModule(val view: SearchContract.View) {
    @ActivityScope
    @Provides
    fun provideView(): SearchContract.View = view

    @ActivityScope
    @Provides
    fun providePresenter(presenter: SearchPresenter): SearchContract.Presenter = presenter
}