package com.example.trending_github_repos.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trending_github_repos.R
import com.example.trending_github_repos.databinding.RepoListItemBinding
import com.example.trending_github_repos.models.Item
import com.squareup.picasso.Picasso

class RepoListAdapter(private val onItemClicked: (Item) -> Unit) :
    ListAdapter<Item, RepoListAdapter.RepoViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding =
            RepoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)


    }

    inner class RepoViewHolder(private val binding: RepoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                txtDescription.text = item.description
                txtName.text = item.full_name
                txtLang.text = item.language
                txtStart.text = item.stargazers_count.toString()
                txtFork.text = item.forks_count.toString()
                Picasso.with(root.context).load(item.owner.avatar_url).into(imgRepo)
                if (item.isSelected == 1)
                    binding.itemLayout.setBackgroundColor(Color.LTGRAY)
                else
                    binding.itemLayout.setBackgroundColor(Color.WHITE)
                binding.root.setOnClickListener {
                    binding.itemLayout.setBackgroundColor(Color.LTGRAY)
                    onItemClicked(item)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

}