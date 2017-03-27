package com.fuh.testapplication.di.module.activity

import com.fuh.testapplication.contract.SearchContract
import com.fuh.testapplication.di.scope.ActivityScope
import com.fuh.testapplication.presenter.SearchPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Nick on 22.03.2017.
 */
@Module
class MainActivityModule(val view: SearchContract.View) {
    @ActivityScope
    @Provides
    fun provideView(): SearchContract.View = view

    @ActivityScope
    @Provides
    fun providePresenter(presenter: SearchPresenter): SearchContract.Presenter = presenter
}