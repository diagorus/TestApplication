package com.fuh.testapplication.presenter

import android.net.Uri
import com.fuh.testapplication.contract.SavedContract
import com.fuh.testapplication.di.scope.ActivityScope
import com.fuh.testapplication.model.Gif
import io.realm.Realm
import java.io.File
import javax.inject.Inject

/**
 * Created by Nick on 26.03.2017.
 */
@ActivityScope
class SavedPresenter
@Inject constructor(val view: SavedContract.View, val realm: Realm) : SavedContract.Presenter {

    override fun start() {
        loadSavedGifs()
    }

    override fun stop() {
        realm.close()
    }

    override fun loadSavedGifs() {
        val data = realm.where(Gif::class.java).findAll()

        if (data.isEmpty()) {
            view.showNoItems()
        }
        view.showSavedGifs(data)
    }

    override fun deleteGif(gif: Gif) {
        realm.executeTransaction {
            val uri = Uri.parse(gif.images!!.fixedHeight!!.url)
            File(uri.path).delete()
            gif.deleteFromRealm()
        }
    }
}