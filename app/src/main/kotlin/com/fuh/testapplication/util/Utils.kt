package com.fuh.testapplication.util

import android.app.Activity
import android.view.inputmethod.InputMethodManager

/**
 * Created by Nick on 25.03.2017.
 */
object Utils {
    fun hideKeyboard(activity: Activity) {
        val view = activity.currentFocus
        view?.let {
            activity.inputMethodManager.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}