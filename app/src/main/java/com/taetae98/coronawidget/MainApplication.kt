package com.taetae98.coronawidget

import android.app.Application
import android.content.Intent
import com.taetae98.coronawidget.widget.KoreaCoronaWidget
import com.taetae98.coronawidget.widget.LocalCoronaWidget
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        sendBroadcast(
            Intent(this, KoreaCoronaWidget::class.java).apply {
                action = KoreaCoronaWidget.WIDGET_UPDATE
            }
        )
        sendBroadcast(
            Intent(this, LocalCoronaWidget::class.java).apply {
                action = LocalCoronaWidget.WIDGET_UPDATE
            }
        )
    }
}