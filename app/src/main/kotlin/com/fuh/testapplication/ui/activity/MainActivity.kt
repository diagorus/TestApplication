package com.fuh.testapplication.ui.activity

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fuh.testapplication.R
import com.fuh.testapplication.adapter.GifsAdapter
import com.fuh.testapplication.contract.SearchContract
import com.fuh.testapplication.di.component.activity.MainActivityComponent
import com.fuh.testapplication.di.module.activity.MainActivityModule
import com.fuh.testapplication.model.Gif
import com.fuh.testapplication.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_no_items.*
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class MainActivity : BaseActivity(), SearchContract.View {

    lateinit var component: MainActivityComponent
    @Inject override lateinit var presenter: SearchContract.Presenter

    lateinit var gifsAdapter: GifsAdapter
    lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarActivityMain)

        with(recyclerActivityMainGifs) {
            val gridLayoutManager = GridLayoutManager(ctx, 2)
            layoutManager = gridLayoutManager

            gifsAdapter = GifsAdapter(mutableListOf<Gif>(), {
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
            }, {
                presenter.saveGif(it)
                true
            })

            scrollListener = object: EndlessRecyclerViewScrollListener(gridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    presenter.loadNextPage(page)
                }
            }
            addOnScrollListener(scrollListener)

            adapter = gifsAdapter
        }

        presenter.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        val searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text) as EditText
        searchEditText.setOnClickListener {
            startSearchActions(searchView)
        }

        searchView.setOnClickListener {
            startSearchActions(searchView)
        }

        searchView.setOnSearchClickListener {
            startSearchActions(searchView)
        }

        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.tintAllIcons(Color.rgb(255, 255, 255))
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean  = when(item.itemId) {
        R.id.saved -> {
            startActivity(SavedActivity.newIntent(this))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun setupDependencies() {
        component = appComponent.mainActivityComponentBuilder()
                .mainActivityModule(MainActivityModule(this))
                .build()

        component.inject(this)
    }

    override fun showFirstResults(data: List<Gif>) {
        gifsAdapter.setGifs(data)
        gifsAdapter.notifyDataSetChanged()
        scrollListener.resetState()
    }

    override fun showNextResults(data: List<Gif>) {
        gifsAdapter.addAllToGifs(data)
        gifsAdapter.notifyDataSetChanged()
    }

    override fun showNoItems() {
        linearLayoutAllNoItems.visibility = View.VISIBLE
        recyclerActivityMainGifs.visibility = View.GONE
    }

    override fun hideNoItems() {
        linearLayoutAllNoItems.visibility = View.GONE
        recyclerActivityMainGifs.visibility = View.VISIBLE
    }

    override fun showSavingSuccessful(gif: Gif) {
        toast(getString(R.string.main_successfulsave, gif.slug))
    }

    override fun showSavingError(gif: Gif) {
        toast(getString(R.string.main_errorsave, gif.slug))
    }

    override fun showSearchError() {
        toast(getString(R.string.main_errorsearch))
    }

    override fun showNextPageError() {
        toast(getString(R.string.main_errornextpage))
    }

    private fun startSearchActions(searchView: SearchView) = searchView.rxQueryText()
            .debounce(200, TimeUnit.MILLISECONDS)
            .filter { it.length > 1 }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                presenter.loadFirstPage(it)
            },{
                toast("Error! ${ it.message }")
            },{
                Utils.hideKeyboard(this)
            })
}
