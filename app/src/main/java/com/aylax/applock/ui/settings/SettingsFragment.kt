package com.aylax.applock.ui.settings

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.aylax.applock.R
import com.aylax.applock.databinding.FragmentSettingsBinding
import com.aylax.applock.ui.base.BaseFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>() {

    override fun attachViewModel(): SettingsViewModel {
        return ViewModelProvider(this)[SettingsViewModel::class.java]
    }

    override fun attachLayout(layoutInflater: LayoutInflater?): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(layoutInflater!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout?.apply {
            lockStylePick.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext()).apply {
                    setTitle("Lock style")
                        .setSingleChoiceItems(
                            R.array.lock_type, 0
                        ) { _: DialogInterface?, _: Int -> }
                        .setPositiveButton("Confirm") { _, _ -> }
                        .create()
                }.show()
            }
        }
    }

}