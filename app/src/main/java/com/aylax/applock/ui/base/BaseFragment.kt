package com.aylax.applock.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<LAYOUT : ViewBinding?, VM : ViewModel?> : Fragment() {

    protected var viewModel: VM? = null
    protected var layout: LAYOUT? = null

    protected abstract fun attachViewModel(): VM
    protected abstract fun attachLayout(layoutInflater: LayoutInflater?): LAYOUT

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = attachLayout(layoutInflater)
        viewModel = attachViewModel()
        return layout!!.root
    }
}