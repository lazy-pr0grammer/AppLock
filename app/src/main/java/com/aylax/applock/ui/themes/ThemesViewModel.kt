package com.aylax.applock.ui.themes

import androidx.lifecycle.LiveData
import com.aylax.applock.core.repository.ThemesRepository
import com.aylax.applock.ui.base.BaseViewModel
import com.aylax.library.model.Theme

class ThemesViewModel : BaseViewModel() {
    private var repository = ThemesRepository()

    fun getThemesWithFilter(): LiveData<List<Theme>> {
        return repository.getThemesWithFilter()
    }
}