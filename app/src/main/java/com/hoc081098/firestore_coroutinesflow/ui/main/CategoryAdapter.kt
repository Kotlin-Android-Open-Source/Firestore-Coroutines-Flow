package com.hoc081098.firestore_coroutinesflow.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hoc081098.firestore_coroutinesflow.GlideRequests
import com.hoc081098.firestore_coroutinesflow.databinding.CategoryItemBinding
import com.hoc081098.firestore_coroutinesflow.domain.entity.Category

class CategoryAdapter(
  private val glide: GlideRequests
) : ListAdapter<Category, CategoryAdapter.VH>(
  object : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category) =
      oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Category, newItem: Category) =
      oldItem == newItem
  }
) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
    return VH(
      CategoryItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

  inner class VH(private val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Category) {
      glide
        .load(item.imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .into(binding.imageView)
      binding.textView.text = item.name
    }
  }
}