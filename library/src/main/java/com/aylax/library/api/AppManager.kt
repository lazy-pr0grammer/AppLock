package com.aylax.library.api

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.aylax.library.model.Application
import com.aylax.library.preference.DataManager

class AppManager(private var context: Context) {
    private var manager: DataManager = DataManager(context)

    @Suppress("DEPRECATION")
    @SuppressLint("QueryPermissionsNeeded", "NewApi")
    fun getApplications(): List<Application> {
        return try {
            val applicationsList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.getInstalledApplications(
                    PackageManager.ApplicationInfoFlags.of(
                        PackageManager.GET_META_DATA.toLong()
                    )
                )
            } else {
                context.packageManager.getInstalledApplications(
                    PackageManager.GET_META_DATA
                )
            }
            val applications = mutableListOf<Application>()
            for (info in applicationsList) {
                if (context.packageManager.getLaunchIntentForPackage(info.packageName) != null) {
                    val app = Application()
                    app.pkg_name = info.packageName
                    app.app_icon = info.loadIcon(context.packageManager)
                    app.app_name = info.loadLabel(context.packageManager).toString()
                    app.is_locked = manager.lockedApplications.contains(app.pkg_name)
                    applications.add(app)
                }
            }
            applications
        } catch (e: PackageManager.NameNotFoundException) {
            emptyList()
        }
    }

    @Suppress("DEPRECATION")
    fun getApplicationInfo(pkg: String): Application {
        val appInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.getApplicationInfo(
                pkg,
                PackageManager.ApplicationInfoFlags.of(0)
            )
        } else {
            context.packageManager.getApplicationInfo(pkg, 0)
        }
        return Application(
            appInfo.loadLabel(context.packageManager).toString(),
            pkg,
            appInfo.loadIcon(context.packageManager),
            manager.lockedApplications.contains(pkg)
        )
    }
}