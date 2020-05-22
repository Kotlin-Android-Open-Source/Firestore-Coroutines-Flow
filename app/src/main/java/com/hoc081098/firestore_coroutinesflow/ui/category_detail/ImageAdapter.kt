package com.hoc081098.firestore_coroutinesflow.ui.category_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hoc081098.firestore_coroutinesflow.GlideRequests
import com.hoc081098.firestore_coroutinesflow.R
import com.hoc081098.firestore_coroutinesflow.databinding.ImageItemBinding
import com.hoc081098.firestore_coroutinesflow.domain.entity.Image

class ImageAdapter(
  private val glide: GlideRequests,
  private val onClickImage: (Image) -> Unit,
) : ListAdapter<Image, ImageAdapter.VH>(object : DiffUtil.ItemCallback<Image>() {
  override fun areItemsTheSame(oldItem: Image, newItem: Image) = oldItem.id == newItem.id
  override fun areContentsTheSame(oldItem: Image, newItem: Image) = oldItem == newItem
}) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
    return VH(
      ImageItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

  inner class VH(private val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
      binding.root.setOnClickListener {
        val position = bindingAdapterPosition
        if (position != NO_POSITION) {
          onClickImage(getItem(position))
        }
      }
    }

    fun bind(item: Image) {
      glide
        .load(item.thumbnailUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .fitCenter()
        .placeholder(R.drawable.icons8_full_image_80)
        .error(R.drawable.icons8_full_image_80)
        .into(binding.imageView)
    }
  }
}