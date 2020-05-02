package com.hoc081098.firestore_coroutinesflow.data.entity

import com.google.firebase.firestore.PropertyName

data class CategoryEntity(
  @get:PropertyName("name")
  @set:PropertyName("name")
  var name: String,
  @get:PropertyName("imageUrl")
  @set:PropertyName("imageUrl")
  var imageUrl: String,
) {
  @Suppress("unused")
  constructor() : this(name = "", imageUrl = "")
}