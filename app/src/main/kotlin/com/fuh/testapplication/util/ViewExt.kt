package com.fuh.testapplication.util

import android.content.Context
import android.support.v7.widget.SearchView
import android.view.View
import rx.Observable

/**
 * Created by Nick on 22.03.2017.
 */
val View.ctx: Context
    get() = context

fun SearchView.rxQueryText() = Observable.create<String> {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
                it.onNext(query)
                it.onCompleted()
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            if (!newText.isEmpty()) {
                it.onNext(newText)
            }
            return true
        }
    })
}