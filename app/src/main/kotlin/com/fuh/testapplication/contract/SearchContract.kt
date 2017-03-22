package com.fuh.testapplication.contract

import com.fuh.testapplication.model.Gif
import com.fuh.testapplication.ui.BaseView

/**
 * Created by Nick on 22.03.2017.
 */
object SearchContract {
    interface View : BaseView<Presenter> {
        fun showResults(result: List<Gif>)
    }
    interface Presenter {
        fun search(query: String)
    }
}