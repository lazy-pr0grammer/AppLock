package com.aylax.applock.ui.apps

import androidx.lifecycle.LiveData
import com.aylax.applock.core.repository.AppsRepository
import com.aylax.applock.ui.base.BaseViewModel
import com.aylax.library.model.Application

class AppsViewModel: BaseViewModel() {
    private var repository = AppsRepository()

    fun getAppsWithFilter(): LiveData<List<Application>> {
        return repository.getAppsWithFilter()
    }
}