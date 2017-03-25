package com.fuh.testapplication.contract

import com.fuh.testapplication.model.Gif
import com.fuh.testapplication.ui.BaseView

/**
 * Created by Nick on 22.03.2017.
 */
object SearchContract {
    interface View : BaseView<Presenter> {
        fun showFirstResults(data: List<Gif>)
        fun showNextResults(data: List<Gif>)
    }

    interface Presenter {
        fun loadFirstPage(q: String)
        fun loadNextPage(page: Int)
    }
}