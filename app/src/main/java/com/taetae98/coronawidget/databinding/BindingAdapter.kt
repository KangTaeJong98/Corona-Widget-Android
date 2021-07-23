package com.taetae98.coronawidget.databinding

import android.util.Log
import android.webkit.WebView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("html")
    fun setHTML(view: WebView, html: String?) {
        view.loadDataWithBaseURL(null, html ?: "", "text/html; charset=utf-8", "UTF-8", null)
    }
}