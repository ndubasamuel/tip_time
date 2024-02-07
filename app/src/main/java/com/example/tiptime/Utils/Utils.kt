package com.example.tiptime.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class Utils {
    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?



            return if (connectivityManager != null) {
                val network = connectivityManager.activeNetwork
                val capabilities = connectivityManager.getNetworkCapabilities(network)
                capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true

            } else {
                false
            }
        }
    }
}

