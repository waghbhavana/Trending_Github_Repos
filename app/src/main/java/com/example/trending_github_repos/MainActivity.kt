package com.example.trending_github_repos

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.trending_github_repos.viewmodels.MainViewModel
import com.example.trending_github_repos.viewmodels.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import java.net.URL
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //   (application as RepositoriesApplication).applicationComponent.inject(this)

           // val deviceOnline = isDeviceOnline(this.applicationContext)
           /// val internetAvailable = isInternetAvailable()
          //  Log.d("tagLog", "isdeviceOnline- $deviceOnline")
           // Log.d("tagLog", "isinternetAvailable- $internetAvailable")


    }
        private fun isDeviceOnline(context: Context): Boolean {
            val connManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
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