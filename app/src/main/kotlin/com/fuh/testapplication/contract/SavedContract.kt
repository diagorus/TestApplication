package com.fuh.testapplication.contract

import com.fuh.testapplication.model.Gif
import com.fuh.testapplication.presenter.BasePresenter
import com.fuh.testapplication.ui.BaseView
import io.realm.RealmResults

/**
 * Created by Nick on 26.03.2017.
 */
object SavedContract {
    interface View : BaseView<Presenter> {
        fun showSavedGifs(data: RealmResults<Gif>)
    }

    interface Presenter : BasePresenter {
        fun loadSavedGifs()
        fun deleteGif(gif: Gif)
    }
}