package com.akshay.mindvalley.home.presentation

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akshay.mindvalley.home.domain.model.MediaItem

abstract class MediaAdapter<T : RecyclerView.ViewHolder?> :
    ListAdapter<MediaItem, T>(DiffCallback()) {

    companion object{
        const  val itemCountLimit = 6
    }

    override fun getItemCount(): Int {
        return if(currentList.size > itemCountLimit) itemCountLimit else  currentList.size
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