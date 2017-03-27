package com.fuh.testapplication.contract

import com.fuh.testapplication.model.Gif
import com.fuh.testapplication.presenter.BasePresenter
import com.fuh.testapplication.ui.BaseView

/**
 * Created by Nick on 22.03.2017.
 */
object SearchContract {
    interface View : BaseView<Presenter> {
        fun showFirstResults(data: List<Gif>)
        fun showNextResults(data: List<Gif>)
        fun showNoItems()
        fun hideNoItems()
    }

    interface Presenter : BasePresenter {
        fun loadFirstPage(q: String)
        fun loadNextPage(page: Int)
        fun saveGif(gif: Gif)
    }
}