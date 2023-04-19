package com.example.trending_github_repos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.trending_github_repos.viewmodels.MainViewModel
import com.example.trending_github_repos.viewmodels.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel:MainViewModel
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as RepositoriesApplication).applicationComponent.inject(this)
        mainViewModel= ViewModelProvider(this,mainViewModelFactory).get(MainViewModel::class.java)

        mainViewModel.reposLiveData.observe(this) {
            Log.d("RESULT", "repos" + it.incomplete_results)
        }
    }
}