package com.taetae98.coronawidget.viewmodel

import android.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(
    stateHandle: SavedStateHandle
) : ViewModel() {
    val color = stateHandle.getLiveData("COLOR", 0)
    val red = stateHandle.getLiveData("RED", 0)
    val blue = stateHandle.getLiveData("BLUE", 0)
    val green = stateHandle.getLiveData("GREEN", 0)
    val alpha = stateHandle.getLiveData("ALPHA", 255)

    init {
        red.observeForever {
            val value = Color.argb(
                alpha.value!!, it, green.value!!, blue.value!!
            )

            if (value != color.value) {
                color.value = value
            }
        }

        blue.observeForever {
            val value = Color.argb(
                alpha.value!!, red.value!!, green.value!!, it
            )

            if (value != color.value) {
                color.value = value
            }
        }

        green.observeForever {
            val value = Color.argb(
                alpha.value!!, red.value!!, it, blue.value!!
            )

            if (value != color.value) {
                color.value = value
            }
        }

        alpha.observeForever {
            val value = Color.argb(
                it, red.value!!, green.value!!, blue.value!!
            )

            if (value != color.value) {
                color.value = value
            }
        }

        color.observeForever {
            val r = Color.red(it)
            val b = Color.blue(it)
            val g = Color.green(it)
            val a = Color.alpha(it)

            if (red.value != r) {
                red.value = r
            }

            if (green.value != g) {
                green.value = g
            }

            if (blue.value != b) {
                blue.value = b
            }

            if (alpha.value != a) {
                alpha.value = a
            }
        }
    }
}