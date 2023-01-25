package com.akshay.mindvalley.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akshay.mindvalley.databinding.ItemSeriesMediaBinding
import com.akshay.mindvalley.home.domain.model.MediaItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class SeriesMediaAdapter :
    MediaAdapter<SeriesMediaAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemSeriesMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(item = currentList[position])
    }

    class ItemViewHolder(val binding: ItemSeriesMediaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MediaItem) = with(binding) {
            title.text = item.title
            description.text = item.channel?.uppercase() ?: ""
            Glide.with(binding.root.context).load(item.coverAsset)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(contentIm)
        }
    }

}
