package com.aylax.applock.ui.screen

import androidx.lifecycle.LiveData
import com.aylax.applock.core.repository.AppsRepository
import com.aylax.applock.ui.base.BaseViewModel
import com.aylax.library.model.Application

class LockViewModel : BaseViewModel() {
    private var repository = AppsRepository()

    fun getAppInfo(pkg: String): LiveData<Application> {
        return repository.getAppsInfo(pkg)
    }
}