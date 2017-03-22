package com.fuh.testapplication.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.fuh.testapplication.App
import com.fuh.testapplication.di.component.AppComponent
import com.fuh.testapplication.util.ctx

/**
 * Created by Nick on 22.03.2017.
 */
abstract class BaseActivity: AppCompatActivity() {

    protected val appComponent: AppComponent = App.get(ctx).appComponent

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setupDependencies()
    }

    abstract protected fun setupDependencies()
}