package com.fuh.testapplication.util.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager

/**
 * Created by Nick on 22.03.2017.
 */
val Activity.inputMethodManager
    get() = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager