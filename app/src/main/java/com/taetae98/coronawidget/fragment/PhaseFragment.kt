package com.taetae98.coronawidget.fragment

import androidx.fragment.app.activityViewModels
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.databinding.BindingFragment
import com.taetae98.coronawidget.databinding.FragmentPhaseBinding
import com.taetae98.coronawidget.viewmodel.PhaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhaseFragment : BindingFragment<FragmentPhaseBinding>(R.layout.fragment_phase) {
    private val viewModel by activityViewModels<PhaseViewModel>()

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.viewModel = viewModel
    }
}