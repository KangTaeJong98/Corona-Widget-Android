package com.taetae98.coronawidget.dto

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalCoronaWidgetInformation(
    @PrimaryKey
    val id: Int = 0,
    val local: String = "",
    val textColor: Int = Color.WHITE,
    val backgroundColor: Int = Color.parseColor("#80000000")
)