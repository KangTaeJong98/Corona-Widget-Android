package com.taetae98.coronawidget.activity

import androidx.navigation.ui.AppBarConfiguration
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.databinding.ActivityLocalCoronaWidgetBinding
import com.taetae98.coronawidget.databinding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocalCoronaWidgetActivity : BindingActivity<ActivityLocalCoronaWidgetBinding>(R.layout.activity_local_corona_widget) {
    override val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.localCoronaWidgetFragment
            )
        )
    }
}