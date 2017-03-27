package com.fuh.testapplication.presenter

import android.net.Uri
import com.fuh.testapplication.contract.SearchContract
import com.fuh.testapplication.di.scope.ActivityScope
import com.fuh.testapplication.model.Gif
import com.fuh.testapplication.model.GiphyApi
import com.fuh.testapplication.util.Utils
import io.realm.Realm
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by Nick on 22.03.2017.
 */
@ActivityScope
class SearchPresenter
    @Inject constructor(val view: SearchContract.View,
                        val model: GiphyApi,
                        val realm: Realm
    ) : SearchContract.Presenter {

    companion object {
        const val RECORDS_ON_PAGE = 30
    }

    var query = ""

    override fun start() {
        view.showNoItems()
    }

    override fun stop() {
        realm.close()
    }

    override fun loadFirstPage(q: String) {
        view.hideNoItems()
        query = getSearchQuery(q)
        model.search(query, 0, RECORDS_ON_PAGE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    if (it.data.isEmpty()) {
                        view.showNoItems()
                    }
                    view.showFirstResults(it.data)
                }, {
                    view.showSearchError()
                })
    }

    override fun loadNextPage(page: Int) {
        model.search(query, RECORDS_ON_PAGE * page, RECORDS_ON_PAGE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    view.showNextResults(it.data)
                }, {
                    view.showNextPageError()
                })
    }

    override fun saveGif(gif: Gif) {
        val futureGifFile = Utils.getFileInAppRoot(gif.slug!!)

        if (futureGifFile.exists()) {
            view.showAlreadySavedError(gif)
            return
        }

        realm.executeTransactionAsync ({
            Utils.downloadFileSync(gif.images!!.original!!.url!!, futureGifFile)

            gif.images!!.original!!.url = Uri.fromFile(futureGifFile).toString()

            it.copyToRealm(gif)
        }, {
            view.showSavingSuccessful(gif)
        }, {
            view.showSavingError(gif)
        })
    }

    private fun getSearchQuery(q: String) = q.replace(' ', '+')

    private fun isAlreadySaved(gif: String) {

    }
}