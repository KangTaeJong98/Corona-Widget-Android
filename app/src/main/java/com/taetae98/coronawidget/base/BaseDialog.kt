package com.taetae98.coronawidget.base

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment

abstract class BaseDialog : DialogFragment() {
    protected fun setLayout(width: Int = ViewGroup.LayoutParams.WRAP_CONTENT, height: Int = ViewGroup.LayoutParams.WRAP_CONTENT) {
        dialog?.window?.setLayout(width, height)
    }

    protected fun setBackground(drawable: Drawable) {
        dialog?.window?.setBackgroundDrawable(drawable)
    }

    protected fun setAnimation(@StyleRes resId: Int) {
        dialog?.window?.attributes?.windowAnimations = resId
    }

    protected fun setSoftInputMode(mode: Int) {
        dialog?.window?.setSoftInputMode(mode)
    }
}