package com.aylax.applock.ui.themes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.aylax.applock.databinding.FragmentThemesBinding
import com.aylax.applock.ui.base.BaseFragment

class ThemesFragment: BaseFragment<FragmentThemesBinding, ThemesViewModel>() {
    override fun attachViewModel(): ThemesViewModel {
        return ViewModelProvider(this)[ThemesViewModel::class.java]
    }

    override fun attachLayout(layoutInflater: LayoutInflater?): FragmentThemesBinding {
        return FragmentThemesBinding.inflate(layoutInflater!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout!!.apply {
            themesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            themesRecyclerView.isNestedScrollingEnabled = false
            themesRecyclerView.setHasFixedSize(true)

            viewModel!!.getThemesWithFilter().observe(viewLifecycleOwner) {
                themesRecyclerView.adapter = ThemesAdapter(it)
                themesRecyclerView.visibility = View.VISIBLE
                indicator.visibility = View.GONE
            }
        }
    }

}