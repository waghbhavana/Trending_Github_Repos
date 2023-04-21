package com.example.trending_github_repos

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
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


    }



}