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



    override fun search(query: String, offset: Int, limit: Int) {
        model.search(query, offset, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.showResults(it.data)
                }
    }
}