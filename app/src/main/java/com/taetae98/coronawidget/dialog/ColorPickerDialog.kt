package com.taetae98.coronawidget.dialog

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.databinding.BindingDialog
import com.taetae98.coronawidget.databinding.DialogColorPickerBinding
import com.taetae98.coronawidget.viewmodel.ColorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColorPickerDialog(private val initColor: Int = Color.BLACK) : BindingDialog<DialogColorPickerBinding>(R.layout.dialog_color_picker) {
    private val viewModel by viewModels<ColorViewModel>()

    var onColorPick: ((color: Int) -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        onCreateColor()
        onCreateOnColorPick()

        return binding.root
    }

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.viewModel = viewModel
    }

    private fun onCreateColor() {
        viewModel.color.value = initColor
    }

    private fun onCreateOnColorPick() {
        binding.setOnColorPick {
            onColorPick?.invoke(viewModel.color.value!!)
            dismissAllowingStateLoss()
        }
    }
}