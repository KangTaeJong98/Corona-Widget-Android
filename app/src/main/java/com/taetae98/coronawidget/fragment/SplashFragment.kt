package com.taetae98.coronawidget.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.databinding.BindingFragment
import com.taetae98.coronawidget.databinding.FragmentSplashBinding

class SplashFragment : BindingFragment<FragmentSplashBinding>(R.layout.fragment_splash) {
    init {
        lifecycleScope.launchWhenResumed {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainActivity())
        }
    }
}