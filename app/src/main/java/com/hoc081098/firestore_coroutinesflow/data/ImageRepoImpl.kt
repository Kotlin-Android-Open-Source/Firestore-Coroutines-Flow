package com.hoc081098.firestore_coroutinesflow.data

import com.google.firebase.firestore.FirebaseFirestore
import com.hoc081098.firestore_coroutinesflow.data.entity.ImageEntity
import com.hoc081098.firestore_coroutinesflow.domain.entity.Category
import com.hoc081098.firestore_coroutinesflow.domain.entity.Image
import com.hoc081098.firestore_coroutinesflow.domain.repo.ImageRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@ExperimentalCoroutinesApi
class ImageRepoImpl(
  private val firestore: FirebaseFirestore,
  private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ImageRepo {
  override fun imagesByCategory(category: Category): Flow<List<Image>> {
    return firestore
      .collection("images")
      .whereEqualTo("categoryId", category.id)
      .orderBy("name")
      .snapshots()
      .map { (querySnapshot, exception) ->
        exception?.let { throw it }
        (querySnapshot ?: return@map emptyList())
          .documents
          .mapNotNull { it.toObject(ImageEntity::class.java)?.toImage(it.id) }
      }
      .flowOn(ioDispatcher)
  }
}

private fun ImageEntity.toImage(id: String): Image {
  return Image(
    id = id,
    name = name,
    imageUrl = imageUrl,
    thumbnailUrl = thumbnailUrl,
    viewCount = viewCount,
    downloadCount = downloadCount,
    categoryId = categoryId,
  )
}
