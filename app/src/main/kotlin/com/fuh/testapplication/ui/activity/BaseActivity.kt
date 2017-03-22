package com.fuh.testapplication.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fuh.testapplication.App
import com.fuh.testapplication.di.component.AppComponent

/**
 * Created by Nick on 22.03.2017.
 */
abstract class BaseActivity: AppCompatActivity() {

    protected val appComponent: AppComponent = App.appComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDependencies()
    }

    abstract protected fun setupDependencies()
}