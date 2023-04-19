package com.example.trending_github_repos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trending_github_repos.databinding.RepoListItemBinding
import com.example.trending_github_repos.models.Item

class RepoListAdapter : ListAdapter<Item,RepoListAdapter.RepoViewHolder> (DiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding= RepoListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val currentItem=getItem(position)
        holder.bind(currentItem)
    }

    class RepoViewHolder(private val binding: RepoListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item){
            binding.apply {
                txtDescription.text=item.description
                txtName.text=item.full_name
                txtLang.text=item.language
                txtStart.text=item.stargazers_count.toString()
                txtFork.text=item.forks_count.toString()
               // Glide.with(this).load("http://i.imgur.com/DvpvklR.png").into(imgRepo);

            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem== newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem== newItem
        }

    }
}