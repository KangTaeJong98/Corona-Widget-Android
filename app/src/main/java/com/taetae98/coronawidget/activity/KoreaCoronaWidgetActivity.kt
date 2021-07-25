package com.taetae98.coronawidget.activity

import androidx.navigation.ui.AppBarConfiguration
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.databinding.ActivityKoreaCoronaWidgetBinding
import com.taetae98.coronawidget.databinding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KoreaCoronaWidgetActivity : BindingActivity<ActivityKoreaCoronaWidgetBinding>(R.layout.activity_korea_corona_widget) {
    override val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.koreaCoronaWidgetFragment
            )
        )
    }
}