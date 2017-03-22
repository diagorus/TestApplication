package com.fuh.testapplication.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
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
        init()
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
            layoutManager = GridLayoutManager(ctx, 3)

            gifsAdapter = GifsAdapter(mutableListOf<Gif>()) {
                //TODO: implement full gif showing
            }
            adapter = gifsAdapter
        }

        //TODO: just loading 2 cat gifs for now
        presenter.search("cat", 0, 2)
    }
}
