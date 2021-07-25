package com.taetae98.coronawidget.databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.taetae98.coronawidget.base.BaseDialog

abstract class BindingDialog<VB: ViewDataBinding>(
    @LayoutRes
    protected val layoutId: Int
) : BaseDialog(), DataBinding<VB> {
    override val binding: VB by lazy { DataBinding.get(this, layoutId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        onCreateViewDataBinding()
        return binding.root
    }

    protected open fun onCreateViewDataBinding() {
        binding.lifecycleOwner = this
    }
}