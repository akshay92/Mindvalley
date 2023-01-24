package com.akshay.mindvalley.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akshay.mindvalley.R
import com.akshay.mindvalley.databinding.ItemChannelBinding
import com.akshay.mindvalley.home.domain.model.ChannelItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ChannelAdapter : ListAdapter<ChannelItem, ChannelAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemChannelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(item = currentList[position])
    }

    class ItemViewHolder(val binding: ItemChannelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChannelItem) = with(binding) {
            Glide.with(binding.root.context).load(item.iconAsset)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(binding.channelIm)
            binding.episodeCountTx.text = if (item.mediaCount == 1)
                binding.root.context.getString(R.string.episode, item.mediaCount)
            else
                binding.root.context.getString(R.string.episodes, item.mediaCount)
            binding.channelNameTx.text = item.title
            binding.recyclerView.layoutManager = LinearLayoutManager(
                binding.recyclerView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            val adapter = ShowMediaContentAdapter()
            binding.recyclerView.adapter = adapter
            adapter.submitList(item.course + item.series)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<ChannelItem>() {

        override fun areItemsTheSame(oldItem: ChannelItem, newItem: ChannelItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ChannelItem, newItem: ChannelItem): Boolean {
            return oldItem == newItem
        }
    }
}