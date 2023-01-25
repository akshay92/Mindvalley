package com.akshay.mindvalley.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.akshay.mindvalley.R
import com.akshay.mindvalley.databinding.ItemCustomBinding

class CustomLayoutAdapter(
    private val layout: LayoutManager,
    private val title: String,
    private val lifecycleOwner: LifecycleOwner,
    private val adapter: ListAdapter<*, *>
) : ListAdapter<String, CustomLayoutAdapter.ItemViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            adapter,
            title,
            layout
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_horizontal_container
    }

    override fun getItemCount(): Int {
        return 1
    }

    class ItemViewHolder(
        val binding: ItemCustomBinding,
        adapter: ListAdapter<*, *>,
        val title: String,
        layout: LayoutManager
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.title.text = title
            binding.recyclerView.layoutManager = layout
            binding.recyclerView.adapter = adapter
        }
    }

    companion object {
        val diffCallback by lazy {
            object :
                DiffUtil.ItemCallback<String>() {

                override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                    return oldItem == newItem
                }
            }
        }
    }
}