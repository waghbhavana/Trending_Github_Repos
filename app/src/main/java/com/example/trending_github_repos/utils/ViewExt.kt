package com.example.trending_github_repos.utils

import android.widget.SearchView

inline fun SearchView.onQueryTextChanged(crossinline listener:(String)-> Unit){
    this.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            return true
        }
    })

}