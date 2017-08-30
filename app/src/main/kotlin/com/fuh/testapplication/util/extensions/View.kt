package com.fuh.testapplication.util.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.fuh.testapplication.R
import io.reactivex.Observable

/**
 * Created by Nick on 22.03.2017.
 */
val View.ctx: Context
    get() = context

fun SearchView.rxQueryText(): Observable<String> =
        Observable.create<String> {
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    it.onNext(query)
                    it.onComplete()

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
    val expandActivitiesButton = it.findViewById<FrameLayout>(R.id.expand_activities_button)
    expandActivitiesButton?.let {
        val image = it.findViewById<ImageView?>(R.id.image)
        image?.let {
            val drawable = it.drawable
            drawable.tint(color)
            it.setImageDrawable(drawable)
        }
    }
}

private fun Drawable.tint(color: Int) {
    val wrapped = DrawableCompat.wrap(this)
    this.mutate()
    DrawableCompat.setTint(wrapped, color)
}