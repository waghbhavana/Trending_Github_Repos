package com.example.trending_github_repos.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trending_github_repos.NetworkResult
import com.example.trending_github_repos.R
import com.example.trending_github_repos.databinding.FragmentRepoListBinding
import com.example.trending_github_repos.models.Item
import com.example.trending_github_repos.utils.onQueryTextChanged
import com.example.trending_github_repos.viewmodels.MainViewModel
import com.example.trending_github_repos.viewmodels.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RepoListFragment : Fragment(R.layout.fragment_repo_list) {

    // private val viewModel: MainViewModel by viewModels()
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var repoListAdapter: RepoListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRepoListBinding.bind(view)
         repoListAdapter = RepoListAdapter(::onItemClicked)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        binding.apply {
            repoRecyclerView.apply {
                adapter = repoListAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(false)
            }
        }

        mainViewModel.reposLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    binding.progress.isVisible = false
                    binding.image.isVisible = false
                    repoListAdapter.submitList(it.data?.items)
                }
                is NetworkResult.Error -> {
                    binding.progress.isVisible = false
                    binding.image.isVisible = true
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                is NetworkResult.Loading -> {
                    binding.progress.isVisible = true
                    binding.image.isVisible = false
                }
            }
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
             mainViewModel.getReposFromDB()
            binding.swipeRefreshLayout.isRefreshing = false
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.onQueryTextChanged {
            mainViewModel.searchNameChanged(it)
        }
    }

    private fun onItemClicked(item: Item) {
        mainViewModel.updateSelectedItem(item.id)
    }
}



