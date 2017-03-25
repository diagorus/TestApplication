package com.fuh.testapplication.presenter

import com.fuh.testapplication.contract.SearchContract
import com.fuh.testapplication.di.scope.ActivityScope
import com.fuh.testapplication.model.GiphyApi
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by Nick on 22.03.2017.
 */
@ActivityScope
class SearchPresenter
    @Inject constructor(val view: SearchContract.View,
                        val model: GiphyApi
    ) : SearchContract.Presenter {

    companion object {
        const val RECORDS_IN_PAGE = 30
    }

    var query = ""

    override fun loadFirstPage(q: String) {
        query = getSearchQuery(q)
        model.search(query, 0, RECORDS_IN_PAGE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.showFirstResults(it.data)
                }
    }

    override fun loadNextPage(page: Int) {
        model.search(query, RECORDS_IN_PAGE * (page - 1), RECORDS_IN_PAGE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.showNextResults(it.data)
                }
    }

    fun getSearchQuery(q: String) = q.replace(' ', '+')
}