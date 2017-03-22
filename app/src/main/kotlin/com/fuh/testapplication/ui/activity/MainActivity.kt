package com.fuh.testapplication.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fuh.testapplication.R
import com.fuh.testapplication.di.component.activity.MainActivityComponent

class MainActivity : BaseActivity() {

    lateinit var component: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setupDependencies() {
        component = appComponent.mainActivityComponentBuilder()
                .build()

        component.inject(this)
    }
}
