package com.aylax.library.model

import android.graphics.drawable.Drawable

data class Application(
    var app_name: String? = "",
    var pkg_name: String? = "",
    var app_icon: Drawable? = null,
    var is_locked: Boolean? = false
)
