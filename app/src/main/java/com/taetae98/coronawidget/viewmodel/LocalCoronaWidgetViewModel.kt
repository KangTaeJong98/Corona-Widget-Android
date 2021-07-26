package com.taetae98.coronawidget.viewmodel

import android.Manifest
import android.app.WallpaperManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LocalCoronaWidgetViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    @ApplicationContext
    context: Context
) : ViewModel() {
    val local = stateHandle.getLiveData("LOCAL", "")
    val textColor = stateHandle.getLiveData("TEXT_COLOR", Color.WHITE)
    val backgroundColor = stateHandle.getLiveData("BACKGROUND_COLOR", Color.parseColor("#80000000"))

    val wallpaper = stateHandle.getLiveData(
        "WALLPAPER",
        if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            WallpaperManager.getInstance(context).drawable
        } else {
            null
        }
    )
}