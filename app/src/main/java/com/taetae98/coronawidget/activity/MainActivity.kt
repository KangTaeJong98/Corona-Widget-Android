package com.taetae98.coronawidget.activity

import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.databinding.ActivityMainBinding
import com.taetae98.coronawidget.databinding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.confirmedFragment, R.id.phaseFragment, R.id.analysisFragment, R.id.guidelineFragment
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateBottomNavigationView()
    }

    private fun onCreateBottomNavigationView() {
        binding.bottomNavigation.setupWithNavController(navController)
    }
}