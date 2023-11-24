package com.aylax.applock.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.elevation.SurfaceColors

abstract class BaseActivity<LAYOUT : ViewBinding?, VM : ViewModel?> : AppCompatActivity() {

    protected open var viewModel: VM? = null
    protected open var layout: LAYOUT? = null

    protected abstract fun attachViewModel(): VM
    protected abstract fun attachLayout(layoutInflater: LayoutInflater?): LAYOUT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = attachLayout(layoutInflater)
        setContentView(layout!!.root)
        viewModel = attachViewModel()
        window.apply {
            statusBarColor = SurfaceColors.SURFACE_2.getColor(this@BaseActivity)
            navigationBarColor = SurfaceColors.SURFACE_2.getColor(this@BaseActivity)
        }
    }
}