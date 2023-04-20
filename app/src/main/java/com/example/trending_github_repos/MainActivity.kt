package com.example.trending_github_repos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.trending_github_repos.viewmodels.MainViewModel
import com.example.trending_github_repos.viewmodels.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     //   (application as RepositoriesApplication).applicationComponent.inject(this)
    }
}