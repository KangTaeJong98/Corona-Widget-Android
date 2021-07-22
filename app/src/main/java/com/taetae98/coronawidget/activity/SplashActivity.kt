package com.taetae98.coronawidget.activity

import androidx.navigation.ui.AppBarConfiguration
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.databinding.ActivitySplashBinding
import com.taetae98.coronawidget.databinding.BindingActivity

class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(R.id.splashFragment)
        )
    }
}