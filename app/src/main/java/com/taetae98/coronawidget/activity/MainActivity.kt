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
                R.id.confirmedFragment, R.id.phaseFragment, R.id.analysisFragment
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateSupportActionBar()
        onCreateBottomNavigationView()
    }

    private fun onCreateSupportActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun onCreateBottomNavigationView() {
        binding.bottomNavigation.setupWithNavController(navController)
    }
}