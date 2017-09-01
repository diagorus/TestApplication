package com.fuh.testapplication.util.extensions

import android.net.Uri
import android.os.Environment
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by lll on 31.08.2017.
 */
fun Uri.downloadSync(outputFile: File) {
    val client = OkHttpClient()
    val request = Request.Builder().url(toString()).build()
    val response = client.newCall(request).execute()
    if (!response.isSuccessful) {
        throw IOException("Failed to download file: " + response)
    }
    val fos = FileOutputStream(outputFile)
    fos.write(response.body()?.bytes())
    fos.close()
}

fun String.getAsFileInAppRoot(): File {
    val rootDir = File(Environment.getExternalStorageDirectory(), "/TestApplication")
    if (!rootDir.exists()) { rootDir.mkdirs() }
    return File(rootDir, "$this.gif")
}