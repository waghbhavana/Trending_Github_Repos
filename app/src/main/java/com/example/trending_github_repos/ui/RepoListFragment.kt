package com.example.trending_github_repos.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trending_github_repos.MainActivity
import com.example.trending_github_repos.NetworkResult
import com.example.trending_github_repos.R
import com.example.trending_github_repos.RepositoriesApplication
import com.example.trending_github_repos.databinding.FragmentRepoListBinding
import com.example.trending_github_repos.utils.NetworkCheck
import com.example.trending_github_repos.utils.onQueryTextChanged
import com.example.trending_github_repos.viewmodels.MainViewModel
import com.example.trending_github_repos.viewmodels.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RepoListFragment : Fragment(R.layout.fragment_repo_list) {

    // private val viewModel: MainViewModel by viewModels()
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRepoListBinding.bind(view)
        val repoListAdapter = RepoListAdapter()
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        binding.apply {
            repoRecyclerView.apply {
                adapter = repoListAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        mainViewModel.reposLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    Log.d("RESULT", "Success, repos ${it.data?.items}")
                    repoListAdapter.submitList(it.data?.items)
                }
                is NetworkResult.Error -> {
                    Log.d("RESULT", "Error,repos ${it.message}")
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                is NetworkResult.Loading -> {}
            }
        }
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
        val searchItem= menu.findItem(R.id.search)
        val searchView= searchItem.actionView as SearchView
        searchView.onQueryTextChanged {
            mainViewModel.searchNameChanged(it)
        }
        }
    }



