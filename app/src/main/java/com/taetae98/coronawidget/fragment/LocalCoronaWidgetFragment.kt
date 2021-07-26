package com.taetae98.coronawidget.fragment

import android.Manifest
import android.app.Activity
import android.app.WallpaperManager
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.databinding.BindingFragment
import com.taetae98.coronawidget.databinding.FragmentLocalCoronaWidgetBinding
import com.taetae98.coronawidget.dialog.ColorPickerDialog
import com.taetae98.coronawidget.dto.LocalCoronaWidgetInformation
import com.taetae98.coronawidget.repository.WidgetRepository
import com.taetae98.coronawidget.viewmodel.LocalCoronaWidgetViewModel
import com.taetae98.coronawidget.widget.LocalCoronaWidget
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import javax.inject.Inject

@AndroidEntryPoint
class LocalCoronaWidgetFragment : BindingFragment<FragmentLocalCoronaWidgetBinding>(R.layout.fragment_local_corona_widget) {
    private val viewModel by viewModels<LocalCoronaWidgetViewModel>()
    private val widgetId by lazy { requireActivity().intent.extras!!.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID) }

    @Inject
    lateinit var widgetRepository: WidgetRepository

    private val onPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            viewModel.wallpaper.value = WallpaperManager.getInstance(requireContext()).drawable
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        onCreateSupportActionBar()
        onCreateLocalTextInputLayout()
        onCreateOnTextColor()
        onCreateOnBackgroundColorClick()
        onCreateOnFinish()

        onPermissionRequest.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        return binding.root
    }

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.viewModel = viewModel
    }

    private fun onCreateSupportActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun onCreateLocalTextInputLayout() {
        var list = emptyList<String>()
        runBlocking(Dispatchers.IO) {
            val document = Jsoup.connect("https://search.daum.net/search?nil_suggest=btn&w=tot&DA=SBC&q=%EC%BD%94%EB%A1%9C%EB%82%98").get()
            val information = document.select("div.wrap_subtab:has(div.cont_info)")

            list = information[0].select("span.txt_location").map { it.text() }
        }

        with(binding.localTextInputLayout.editText as AutoCompleteTextView) {
            setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, list))
            setText(list.first(), false)
            setOnItemClickListener { _, _, position, _ ->
                viewModel.local.value = list[position]
            }
        }
    }

    private fun onCreateOnTextColor() {
        binding.setOnTextColor {
            ColorPickerDialog(viewModel.textColor.value!!).apply {
                onColorPick = {
                    viewModel.textColor.value = it
                }
            }.show(parentFragmentManager, null)
        }
    }

    private fun onCreateOnBackgroundColorClick() {
        binding.setOnBackgroundColor {
            ColorPickerDialog(viewModel.backgroundColor.value!!).apply {
                onColorPick = {
                    viewModel.backgroundColor.value = it
                }
            }.show(parentFragmentManager, null)
        }
    }

    private fun onCreateOnFinish() {
        binding.setOnFinish {
            CoroutineScope(Dispatchers.IO).launch {
                widgetRepository.insert(
                    LocalCoronaWidgetInformation(widgetId, viewModel.local.value!!, viewModel.textColor.value!!, viewModel.backgroundColor.value!!)
                )
                requireContext().sendBroadcast(
                    Intent(requireContext(), LocalCoronaWidget::class.java).apply {
                        action = LocalCoronaWidget.WIDGET_UPDATE
                    }
                )
                setResult(Activity.RESULT_OK, Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId))
                finish()
            }
        }
    }
}