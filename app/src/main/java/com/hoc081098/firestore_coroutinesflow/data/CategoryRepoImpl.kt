package com.hoc081098.firestore_coroutinesflow.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.hoc081098.firestore_coroutinesflow.data.entity.CategoryEntity
import com.hoc081098.firestore_coroutinesflow.domain.entity.Category
import com.hoc081098.firestore_coroutinesflow.domain.repo.CategoryRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@ExperimentalCoroutinesApi
class CategoryRepoImpl(
  private val firestore: FirebaseFirestore,
  private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : CategoryRepo {
  override fun watchCategories(): Flow<List<Category>> {
    return firestore
      .collection("categories")
      .orderBy("name")
      .snapshots()
      .map { (querySnapshot, exception) ->
        exception?.let { throw it }
        (querySnapshot ?: return@map emptyList())
          .documents
          .mapNotNull { it.toObject(CategoryEntity::class.java)?.toCategory(it.id) }
      }
      .flowOn(ioDispatcher)
  }
}

private fun CategoryEntity.toCategory(id: String): Category {
  return Category(
    name = name,
    imageUrl = imageUrl,
    id = id
  )
}

@ExperimentalCoroutinesApi
fun Query.snapshots(): Flow<Pair<QuerySnapshot?, FirebaseFirestoreException?>> {
  return callbackFlow {
    val listener = addSnapshotListener { querySnapshot, exception ->
      offer(querySnapshot to exception)
    }
    awaitClose {
      listener.remove()
      Log.d("Query.snapshots", "Remove listener $listener for query ${this@snapshots}")
    }
  }
}