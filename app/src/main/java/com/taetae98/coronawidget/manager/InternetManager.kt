package com.taetae98.coronawidget.manager

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InternetManager @Inject constructor(
    @ApplicationContext
    context: Context
) {
    val manager by lazy { context.getSystemService(ConnectivityManager::class.java) }

    fun isConnected(): Boolean {
        val capabilities = manager.getNetworkCapabilities(manager.activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}