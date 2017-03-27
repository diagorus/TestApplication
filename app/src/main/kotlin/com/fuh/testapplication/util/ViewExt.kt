package com.fuh.testapplication.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.fuh.testapplication.R
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

fun Menu.tintAllIcons(color: Int) {
    (0 until size())
            .map { getItem(it) }
            .forEach {
                it.tintIcon(color)
                it.tintShareIconIfPresent(color)
            }
}

fun MenuItem.tintIcon(color: Int)  = icon?.let {
    it.tint(color)
    icon = it
}

fun MenuItem.tintShareIconIfPresent(color: Int) = actionView?.let {
    val expandActivitiesButton = it.findViewById(R.id.expand_activities_button)
    expandActivitiesButton?.let { v ->
        val image = v.findViewById(R.id.image) as ImageView?
        image?.let { v ->
            val drawable = v.drawable
            drawable.tint(color)
            v.setImageDrawable(drawable)
        }
    }
}

private fun Drawable.tint(color: Int) {
    val wrapped = DrawableCompat.wrap(this)
    this.mutate()
    DrawableCompat.setTint(wrapped, color)
}