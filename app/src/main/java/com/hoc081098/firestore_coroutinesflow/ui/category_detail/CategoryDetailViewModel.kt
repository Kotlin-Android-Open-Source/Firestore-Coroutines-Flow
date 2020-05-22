package com.hoc081098.firestore_coroutinesflow.ui.category_detail

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hoc081098.firestore_coroutinesflow.Lce
import com.hoc081098.firestore_coroutinesflow.domain.entity.Category
import com.hoc081098.firestore_coroutinesflow.domain.entity.Image
import com.hoc081098.firestore_coroutinesflow.domain.repo.ImageRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.LazyThreadSafetyMode.NONE

@ExperimentalCoroutinesApi
class CategoryDetailViewModel(
  private val category: Category,
  private val imageRepo: ImageRepo,
) : ViewModel(), Observer<Lce<List<Image>>> {

  private val lazyData = lazy(NONE) {
    imageRepo.imagesByCategory(category)
      .map { Lce.content(it) }
      .onStart { emit(Lce.loading()) }
      .catch { emit(Lce.error(it)) }
      .asLiveData(timeoutInMs = 0)
      .apply { observeForever(this@CategoryDetailViewModel) }
  }

  val imagesData by lazyData

  override fun onChanged(t: Lce<List<Image>>?) = Unit

  override fun onCleared() {
    super.onCleared()
    if (lazyData.isInitialized()) {
      imagesData.removeObserver(this)
    }
  }
}