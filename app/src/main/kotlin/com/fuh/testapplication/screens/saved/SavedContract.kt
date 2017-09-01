package com.fuh.testapplication.screens.saved

import com.fuh.testapplication.model.Gif
import com.fuh.testapplication.util.BasePresenter
import com.fuh.testapplication.util.BaseView
import io.realm.RealmResults

/**
 * Created by Nick on 26.03.2017.
 */
object SavedContract {
    interface View : BaseView<Presenter> {
        fun showSavedGifs(data: RealmResults<Gif>)
        fun showNoItems()
        fun hideNoItems()
    }

    interface Presenter : BasePresenter {
        fun loadSavedGifs()
        fun deleteGif(gif: Gif)
    }
}