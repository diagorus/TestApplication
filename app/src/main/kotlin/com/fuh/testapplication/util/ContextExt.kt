package com.fuh.testapplication.util

import android.content.Context
import android.widget.Toast

/**
 * Created by Nick on 25.03.2017.
 */
fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()