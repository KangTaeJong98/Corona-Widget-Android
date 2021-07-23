package com.taetae98.coronawidget.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuidelineViewModel @Inject constructor(
    stateHandle: SavedStateHandle
) : ViewModel() {
    val imageUrl = "http://ncov.mohw.go.kr/static/image/content/cm_img_t.png"
}