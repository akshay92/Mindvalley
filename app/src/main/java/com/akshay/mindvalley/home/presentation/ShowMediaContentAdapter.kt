package com.akshay.mindvalley.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akshay.mindvalley.databinding.ItemMediaBinding
import com.akshay.mindvalley.home.domain.model.MediaItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ShowMediaContentAdapter :
    ListAdapter<MediaItem, ShowMediaContentAdapter.ItemViewHolder>(DiffCallback()) {

    private val itemCountLimit = 6

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(item = currentList[position])
    }

    override fun getItemCount(): Int {
        return if(currentList.size > itemCountLimit) itemCountLimit else  currentList.size
    }

    class ItemViewHolder(val binding: ItemMediaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MediaItem) = with(binding) {
            title.text = item.title
            description.text = item.channel?.uppercase() ?: ""
            Glide.with(binding.root.context).load(item.coverAsset)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(contentIm)

        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<MediaItem>() {

        override fun areItemsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
            return oldItem == newItem
        }
    }
}