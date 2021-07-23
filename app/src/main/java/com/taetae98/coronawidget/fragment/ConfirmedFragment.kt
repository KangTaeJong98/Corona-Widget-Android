package com.taetae98.coronawidget.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.databinding.BindingFragment
import com.taetae98.coronawidget.databinding.FragmentConfirmedBinding
import com.taetae98.coronawidget.viewmodel.ConfirmedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmedFragment : BindingFragment<FragmentConfirmedBinding>(R.layout.fragment_confirmed) {
    private val viewModel by activityViewModels<ConfirmedViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        onCreateWebView()

        return binding.root
    }

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.viewModel = viewModel
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun onCreateWebView() {
        with(binding.webView) {
            settings.javaScriptEnabled = true
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()
        }
    }
}