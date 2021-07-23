package com.taetae98.coronawidget.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.manager.InternetManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var internetManager: InternetManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (internetManager.isConnected()) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, R.string.connect_internet, Toast.LENGTH_SHORT).show()
        }

        finish()
    }
}