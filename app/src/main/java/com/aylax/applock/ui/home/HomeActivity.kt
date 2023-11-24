package com.aylax.applock.ui.home

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.accessibility.AccessibilityManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.aylax.applock.R
import com.aylax.applock.core.service.LockService
import com.aylax.applock.databinding.ActivityHomeBinding
import com.aylax.applock.ui.apps.AppsFragment
import com.aylax.applock.ui.base.BaseActivity
import com.aylax.applock.ui.settings.SettingsFragment
import com.aylax.applock.util.Util
import com.aylax.library.preference.DataManager
import com.google.android.material.elevation.SurfaceColors


class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    private var launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            if (hasAccessibilityPermission()) {
                startForegroundService()
            } else {
                Toast.makeText(this, "Accessibility permission not granted", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun attachViewModel(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun attachLayout(layoutInflater: LayoutInflater?): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataManager(this).lockPin = "1234"
        if (!hasAccessibilityPermission()) {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            launcher.launch(intent)
        }
        layout?.apply {
            setSupportActionBar(toolbar)
            Util.toolbarFont(toolbar)
            Util.navigationFont(navigation)
            toolbar.setBackgroundColor(SurfaceColors.SURFACE_2.getColor(this@HomeActivity))

            navigation.setOnItemSelectedListener {
                toolbar.title = it.title
                when (it.itemId) {
                    R.id.apps -> {
                        Util.loadFragment(AppsFragment(), this@HomeActivity)
                        true
                    }

                    /**R.id.themes -> {
                        Util.loadFragment(ThemesFragment(), this@HomeActivity)
                        true
                    }**/

                    R.id.settings -> {
                        Util.loadFragment(SettingsFragment(), this@HomeActivity)
                        true
                    }

                    else -> true
                }
            }
        }
    }

    private fun startForegroundService() {
        val serviceIntent = Intent(this, LockService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.startForegroundService(serviceIntent)
        } else {
            this.startService(serviceIntent)
        }

    }

    private fun hasAccessibilityPermission(): Boolean {
        val accessibilityManager = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        return accessibilityManager.isEnabled

    }

}