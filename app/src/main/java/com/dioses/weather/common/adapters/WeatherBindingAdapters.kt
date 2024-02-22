package com.dioses.weather.common.adapters

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isGone")
fun bindingIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}