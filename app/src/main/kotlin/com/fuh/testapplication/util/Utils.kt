package com.fuh.testapplication.util

import android.app.Activity
import android.os.Environment
import android.view.inputmethod.InputMethodManager
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 * Created by Nick on 25.03.2017.
 */
object Utils {
    fun hideKeyboard(activity: Activity) {
        val view = activity.currentFocus
        view?.let {
            activity.inputMethodManager
                    .hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    fun downloadFileSync(url: String, file: File) {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            throw IOException("Failed to download file: " + response)
        }
        val fos = FileOutputStream(file)
        fos.write(response.body().bytes())
        fos.close()
    }

    fun getFileInAppRoot(name: String): File {
        val rootDir = File(Environment.getExternalStorageDirectory(), "/TestApplicationData")
        if (!rootDir.exists()) { rootDir.mkdir() }
        return File(rootDir, "$name.gif")
    }
}