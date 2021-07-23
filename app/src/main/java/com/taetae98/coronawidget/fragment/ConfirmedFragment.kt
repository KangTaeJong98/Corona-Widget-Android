package com.taetae98.coronawidget.fragment

import androidx.fragment.app.activityViewModels
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.databinding.BindingFragment
import com.taetae98.coronawidget.databinding.FragmentConfirmedBinding
import com.taetae98.coronawidget.viewmodel.ConfirmedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmedFragment : BindingFragment<FragmentConfirmedBinding>(R.layout.fragment_confirmed) {
    private val viewModel by activityViewModels<ConfirmedViewModel>()

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.viewModel = viewModel
    }
}