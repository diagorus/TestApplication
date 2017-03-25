package com.fuh.testapplication.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fuh.testapplication.R
import com.fuh.testapplication.contract.SearchContract
import com.fuh.testapplication.di.component.activity.MainActivityComponent
import com.fuh.testapplication.di.module.activity.MainActivityModule
import com.fuh.testapplication.model.Gif
import com.fuh.testapplication.util.GifsAdapter
import com.fuh.testapplication.util.ctx
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), SearchContract.View {

    lateinit var component: MainActivityComponent
    @Inject override lateinit var presenter: SearchContract.Presenter

    lateinit var gifsAdapter: GifsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarActivityMain)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)

        return true
    }

    override fun setupDependencies() {
        component = appComponent.mainActivityComponentBuilder()
                .mainActivityModule(MainActivityModule(this))
                .build()

        component.inject(this)
    }

    override fun showResults(result: List<Gif>) {
        gifsAdapter.gifs.addAll(result)
        gifsAdapter.notifyDataSetChanged()
    }

    private fun init() {
        with(recyclerActivityMainGifs) {
            layoutManager = GridLayoutManager(ctx, 2)

            gifsAdapter = GifsAdapter(mutableListOf<Gif>()) {
                ctx, uri, thumbRequest, imageView, isPlaying ->
                if (!isPlaying) {
                    Glide
                            .with(ctx)
                            .load(uri)
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .override(150, 150)
                            .placeholder(R.drawable.thumb_image_loading)
                            .error(R.drawable.thumb_image_error)
                            .thumbnail(thumbRequest)
                            .dontAnimate()
                            .into(imageView)
                } else {
                    thumbRequest.into(imageView)
                }
            }

            adapter = gifsAdapter
        }

        //TODO: just loading 7 cat gifs for now
        presenter.search("cat", 0, 7)
    }
}
