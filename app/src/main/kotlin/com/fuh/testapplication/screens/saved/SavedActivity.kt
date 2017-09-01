package com.fuh.testapplication.screens.saved

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.fuh.testapplication.R
import com.fuh.testapplication.model.Gif
import com.fuh.testapplication.util.BaseActivity
import com.fuh.testapplication.util.extensions.ctx
import io.realm.RealmChangeListener
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_saved.*
import kotlinx.android.synthetic.main.layout_no_items.*
import javax.inject.Inject

class SavedActivity : BaseActivity(), SavedContract.View {

    companion object {
        fun newIntent(ctx: Context) = Intent(ctx, SavedActivity::class.java)
    }

    lateinit var component: SavedActivityComponent
    @Inject override lateinit var presenter: SavedContract.Presenter

    lateinit var savedGifsAdapter: SavedGifsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initRecycler()

        presenter.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.stop()
    }

    override fun setupDependencies() {
        component = appComponent.savedActivityComponentBuilder()
                .savedActivityModule(SavedActivityModule(this))
                .build()

        component.inject(this)
    }

    override fun showSavedGifs(data: RealmResults<Gif>) {
        savedGifsAdapter.setSavedGifs(data)
    }

    override fun showNoItems() {
        linearLayoutAllNoItems.visibility = View.VISIBLE
        recyclerActivitySavedGifs.visibility = View.GONE
    }

    override fun hideNoItems() {
        linearLayoutAllNoItems.visibility = View.GONE
        recyclerActivitySavedGifs.visibility = View.VISIBLE
    }

    fun initRecycler() = with(recyclerActivitySavedGifs) {
        val gridLayoutManager = GridLayoutManager(ctx, 2)
        layoutManager = gridLayoutManager

        savedGifsAdapter = SavedGifsAdapter(RealmChangeListener<RealmResults<Gif>> {
            if (it.isEmpty()) {
                showNoItems()
            }
            savedGifsAdapter.notifyDataSetChanged()
        }, { imageView, thumbRequest, actualGifRequest, isPlaying ->
            if (isPlaying) {
                thumbRequest.into(imageView)
            } else {
                actualGifRequest.into(imageView)
            }
        }, {
            presenter.deleteGif(it)
            true
        })

        adapter = savedGifsAdapter
    }
}
