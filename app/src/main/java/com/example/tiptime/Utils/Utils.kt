package com.example.tiptime.Utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

class Utils {
    companion object {
        fun isNetworkAvailable(context: Context?): Boolean {
//fun isNetworkAvailable(application: Application?): Boolean {
            if (context == null) {
                return false
            }

            val connectivityManager =
                context.getSystemService(Application.CONNECTIVITY_SERVICE) as ConnectivityManager?

            return if (connectivityManager != null
            ) {
                val network = connectivityManager.activeNetwork
                val capabilities = connectivityManager.getNetworkCapabilities(network)
                (capabilities != null) && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
            } else {
                false
            }
        }
    }

}


