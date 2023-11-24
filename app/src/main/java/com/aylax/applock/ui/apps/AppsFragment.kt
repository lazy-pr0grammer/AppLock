package com.aylax.applock.ui.apps

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.aylax.applock.databinding.FragmentAppsBinding
import com.aylax.applock.ui.base.BaseFragment
import com.aylax.applock.util.Util
import com.aylax.library.model.Application
import com.aylax.library.preference.DataManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AppsFragment : BaseFragment<FragmentAppsBinding, AppsViewModel>(),
    AppsAdapter.OnClickListener {

    override fun attachViewModel(): AppsViewModel {
        return ViewModelProvider(this)[AppsViewModel::class.java]
    }

    override fun attachLayout(layoutInflater: LayoutInflater?): FragmentAppsBinding {
        return FragmentAppsBinding.inflate(layoutInflater!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout!!.apply {
            appsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            appsRecyclerView.isNestedScrollingEnabled = false
            appsRecyclerView.setHasFixedSize(true)

            viewModel!!.getAppsWithFilter().observe(viewLifecycleOwner) {
                appsRecyclerView.adapter = AppsAdapter(it, this@AppsFragment)
                appsRecyclerView.visibility = View.VISIBLE
                indicator.visibility = View.GONE
            }
        }
    }

    override fun onItemClicked(application: Application) {
        val manager = DataManager(requireContext())
        if (application.is_locked == true) {
            MaterialAlertDialogBuilder(requireContext()).apply {
                setTitle("Action")
                setMessage("Do you want to unlock ${application.app_name}?")
                setPositiveButton("Lock") { _: DialogInterface, _: Int ->
                    manager.unlockApplication(application.pkg_name!!)
                    Util.showToast(requireContext(), "Unlocked successfully!")
                }
                setNegativeButton("No") { _: DialogInterface, _: Int ->

                }
            }.create().show()
        } else {
            MaterialAlertDialogBuilder(requireContext()).apply {
                setTitle("Action")
                setMessage("Do you want to lock ${application.app_name}?")
                setPositiveButton("Lock") { _: DialogInterface, _: Int ->
                    manager.lockApplication(application.pkg_name!!)
                    Util.showToast(requireContext(), "Locked successfully!")
                }
                setNegativeButton("No") { _: DialogInterface, _: Int ->

                }
            }.create().show()
        }
    }
}