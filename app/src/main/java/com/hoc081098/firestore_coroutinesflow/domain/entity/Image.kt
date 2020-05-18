package com.hoc081098.firestore_coroutinesflow.domain.entity

data class Image(
  val id: String,
  val name: String,
  val imageUrl: String,
  val thumbnailUrl: String,
  val viewCount: Long,
  val downloadCount: Long,
  val categoryId: String,
)