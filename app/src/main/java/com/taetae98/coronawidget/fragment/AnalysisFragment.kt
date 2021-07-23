package com.taetae98.coronawidget.fragment

import androidx.fragment.app.activityViewModels
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.databinding.BindingFragment
import com.taetae98.coronawidget.databinding.FragmentAnalysisBinding
import com.taetae98.coronawidget.viewmodel.AnalysisViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnalysisFragment : BindingFragment<FragmentAnalysisBinding>(R.layout.fragment_analysis) {
    private val viewModel by activityViewModels<AnalysisViewModel>()

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.viewModel = viewModel
    }
}