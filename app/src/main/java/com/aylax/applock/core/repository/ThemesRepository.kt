package com.aylax.applock.core.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aylax.library.model.Theme

class ThemesRepository {
    fun getThemesWithFilter(): LiveData<List<Theme>> {
        val result = MutableLiveData<List<Theme>>()
        val list = ArrayList<Theme>()
        for (i in 0 until 10) {
            list.add(Theme("a", "a", 0XFFFFFF, "a"))
        }
        result.value = list
        return result
    }
}