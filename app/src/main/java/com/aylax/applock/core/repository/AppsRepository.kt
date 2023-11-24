package com.aylax.applock.core.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aylax.applock.App
import com.aylax.applock.util.Executor
import com.aylax.library.api.AppManager
import com.aylax.library.model.Application

class AppsRepository {
    fun getAppsWithFilter(): LiveData<List<Application>> {
        val result = MutableLiveData<List<Application>>()
        Executor().execute {
            result.postValue(AppManager(App.getContext()).getApplications())
        }
        return result
    }

    fun getAppsInfo(pkg: String): LiveData<Application> {
        val result = MutableLiveData<Application>()
        result.value = AppManager(App.getContext()).getApplicationInfo(pkg)
        return result
    }
}