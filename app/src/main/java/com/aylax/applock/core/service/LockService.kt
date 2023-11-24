package com.aylax.applock.core.service

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.content.Intent
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import com.aylax.applock.databinding.LayoutPinViewBinding
import com.aylax.applock.ui.screen.LockActivity
import com.aylax.applock.util.Util
import com.aylax.library.preference.DataManager

class LockService : AccessibilityService() {

    private lateinit var manager: DataManager
    private lateinit var windowManager: WindowManager
    private lateinit var binding: LayoutPinViewBinding

    override fun onCreate() {
        super.onCreate()
        //windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    override fun onDestroy() {
        super.onDestroy()
        //windowManager.removeView(binding.root)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        manager = DataManager(this)
        if (event!!.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName?.toString()
            if (manager.lockedApplications.contains(packageName)) {
                if (packageName != manager.currentApplication.pkg_name) {
                    val intent = Intent(this, LockActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra("package", packageName)
                    startActivity(intent)
                }
            }
            if (Util.isScreenOff(this)) {
                manager.clearCurrentApplication()
            }
        }
    }

    override fun onInterrupt() {
    }
}