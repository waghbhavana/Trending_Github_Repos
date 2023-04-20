package com.example.trending_github_repos.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class NetworkCheck {
    companion object{
         fun isDeviceOnline(context: Context): Boolean {
            val connManager = context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connManager.getNetworkCapabilities(connManager.activeNetwork)
                if (networkCapabilities == null) {
                    Log.d("tagLog", "Device Offline")
                    return false
                } else {
                    Log.d("tagLog", "Device Online")
                    if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) &&
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_SUSPENDED)
                    ) {
                        Log.d("tagLog", "Connected to Internet")
                        return true
                    } else {
                        Log.d("tagLog", "Not connected to Internet")
                        return false
                    }
                }
            } else {
                // below Marshmallow
                val activeNetwork = connManager.activeNetworkInfo
                return activeNetwork?.isConnectedOrConnecting == true && activeNetwork.isAvailable
            }
        }
    }
}