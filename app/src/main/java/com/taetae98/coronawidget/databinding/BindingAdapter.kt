package com.taetae98.coronawidget.databinding

import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("html")
    fun setHTML(view: WebView, html: String?) {
        view.loadDataWithBaseURL(null, html ?: "", "text/html; charset=utf-8", "UTF-8", null)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(view: ImageView, url: String?) {
        if (url == null) {
            view.setImageDrawable(null)
            return
        }

        Glide.with(view).load(url).into(view)
    }
}