package com.hoc081098.firestore_coroutinesflow.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
  val id: String,
  val name: String,
  val imageUrl: String,
) : Parcelable