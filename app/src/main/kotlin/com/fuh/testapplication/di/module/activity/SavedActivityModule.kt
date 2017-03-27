package com.fuh.testapplication.di.module.activity

import com.fuh.testapplication.contract.SavedContract
import com.fuh.testapplication.di.scope.ActivityScope
import com.fuh.testapplication.presenter.SavedPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Nick on 26.03.2017.
 */
@Module
class SavedActivityModule(val view: SavedContract.View) {
    @ActivityScope
    @Provides
    fun provideView(): SavedContract.View = view

    @ActivityScope
    @Provides
    fun providePresenter(presenter: SavedPresenter): SavedContract.Presenter = presenter
}