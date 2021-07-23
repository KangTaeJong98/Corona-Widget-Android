package com.taetae98.coronawidget.fragment

import androidx.fragment.app.activityViewModels
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.databinding.BindingFragment
import com.taetae98.coronawidget.databinding.FragmentGuidelineBinding
import com.taetae98.coronawidget.viewmodel.GuidelineViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuidelineFragment : BindingFragment<FragmentGuidelineBinding>(R.layout.fragment_guideline) {
    private val viewModel by activityViewModels<GuidelineViewModel>()

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.viewModel = viewModel
    }
}