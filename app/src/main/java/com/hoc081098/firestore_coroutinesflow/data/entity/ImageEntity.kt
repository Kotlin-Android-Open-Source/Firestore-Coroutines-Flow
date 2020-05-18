package com.hoc081098.firestore_coroutinesflow.data.entity

import com.google.firebase.firestore.PropertyName

data class ImageEntity(
  @get:PropertyName("name")
  @set:PropertyName("name")
  var name: String,
  @get:PropertyName("imageUrl")
  @set:PropertyName("imageUrl")
  var imageUrl: String,
  @get:PropertyName("thumbnailUrl")
  @set:PropertyName("thumbnailUrl")
  var thumbnailUrl: String,
  @get:PropertyName("viewCount")
  @set:PropertyName("viewCount")
  var viewCount: Long,
  @get:PropertyName("downloadCount")
  @set:PropertyName("downloadCount")
  var downloadCount: Long,
  @get:PropertyName("categoryId")
  @set:PropertyName("categoryId")
  var categoryId: String,
)