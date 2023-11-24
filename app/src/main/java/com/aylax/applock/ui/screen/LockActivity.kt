package com.aylax.applock.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.aylax.applock.R
import com.aylax.applock.databinding.ActivityLockBinding
import com.aylax.applock.databinding.LayoutPatternViewBinding
import com.aylax.applock.databinding.LayoutPinViewBinding
import com.aylax.applock.ui.base.BaseActivity
import com.aylax.applock.util.Util
import com.aylax.library.preference.DataManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LockActivity : BaseActivity<ActivityLockBinding, LockViewModel>() {

    override fun attachViewModel(): LockViewModel {
        return ViewModelProvider(this)[LockViewModel::class.java]
    }

    override fun attachLayout(layoutInflater: LayoutInflater?): ActivityLockBinding {
        return ActivityLockBinding.inflate(layoutInflater!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val manager = DataManager(this)
        Util.transparentStatusBar(window)
        Util.transparentNavigationBar(window)
        val pkg = intent.getStringExtra("package")

        val binding = LayoutPinViewBinding.inflate(layoutInflater)
        binding.apply {
            viewModel!!.getAppInfo(pkg!!).observe(this@LockActivity) {
                appIcon.setImageDrawable(it.app_icon)
                instruction.text = String.format(
                    this@LockActivity.resources.getString(R.string.pin_screen_instruction),
                    it.app_name
                )
            }
            zero.setOnClickListener {
                if (!manager.currentApplication.pkg_name.equals(pkg)) {
                    //manager.clearCurrentApplication()
                    manager.setCurrentApplication(pkg, false)
                    Log.d("CurrentData", manager.currentApplication.pkg_name!!)

                    CoroutineScope(Dispatchers.IO).launch {
                        delay(50)
                        finishAndRemoveTask()
                    }
                }
            }
        }

        val patternViewBinding = LayoutPatternViewBinding.inflate(layoutInflater)

        layout!!.base.addView(binding.root)
        overridePendingTransition(
            androidx.appcompat.R.anim.abc_fade_in,
            androidx.appcompat.R.anim.abc_fade_out
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        return
    }
}