package com.aylax.library.preference

import android.content.Context
import android.content.SharedPreferences
import com.aylax.library.model.Current
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataManager(context: Context) {
    private val preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences("app_lock_db", Context.MODE_PRIVATE)
    }

    fun lockApplication(pkg: String) {
        val arrayList = lockedApplications
        if (arrayList.contains(pkg)) {
            arrayList.remove(pkg)
            arrayList.add(pkg)
        } else {
            arrayList.add(pkg)
        }
        preferences.edit().putString("locked_applications", Gson().toJson(arrayList)).apply()
    }

    fun unlockApplication(pkg: String) {
        val arrayList = lockedApplications
        arrayList.remove(pkg)
        preferences.edit().putString("locked_applications", Gson().toJson(arrayList)).apply()
    }

    val currentApplication: Current
        get() {
            val json = preferences.getString("current_application", "{}")
            return Gson().fromJson(json, object : TypeToken<Current?>() {}.type)
        }

    fun setCurrentApplication(pkg: String?, locked: Boolean) {
        val current = Current()
        current.pkg_name = pkg
        current.is_locked = locked
        preferences.edit().putString("current_application", Gson().toJson(current)).apply()
    }

    fun clearCurrentApplication() {
        preferences.edit().remove("current_application").apply()
    }

    var lockPin: String?
        get() = preferences.getString("lock_pin", "1234")
        set(pin) {
            preferences.edit().putString("lock_pin", pin).apply()
        }
    val lockedApplications: MutableList<String>
        get() {
            val json = preferences.getString("locked_applications", "[]")
            return Gson().fromJson(json, object : TypeToken<List<String?>?>() {}.type)
        }
}