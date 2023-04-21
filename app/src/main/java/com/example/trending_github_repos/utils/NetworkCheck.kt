package com.example.trending_github_repos.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class NetworkCheck {
    companion object {
        fun isDeviceOnline(context: Context): Boolean {
            val connManager =
                context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities =
                    connManager.getNetworkCapabilities(connManager.activeNetwork)
                if (networkCapabilities == null) {
                    false
                } else {
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) &&
                            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_SUSPENDED)
                }
            } else {
                // below Marshmallow
                val activeNetwork = connManager.activeNetworkInfo
                activeNetwork?.isConnectedOrConnecting == true && activeNetwork.isAvailable
            }
        }
    }
}