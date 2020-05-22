package com.hoc081098.firestore_coroutinesflow.ui.main

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hoc081098.firestore_coroutinesflow.Lce
import com.hoc081098.firestore_coroutinesflow.domain.entity.Category
import com.hoc081098.firestore_coroutinesflow.domain.repo.CategoryRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.LazyThreadSafetyMode.NONE

@ExperimentalCoroutinesApi
class MainViewModel(categoryRepo: CategoryRepo) : ViewModel(), Observer<Lce<List<Category>>> {

  private val lazyData = lazy(NONE) {
    categoryRepo.watchCategories()
      .map { Lce.content(it) }
      .onStart { emit(Lce.loading()) }
      .catch { emit(Lce.error(it)) }
      .asLiveData(timeoutInMs = 0)
      .apply { observeForever(this@MainViewModel) }
  }

  val categoriesData by lazyData

  override fun onChanged(t: Lce<List<Category>>?) = Unit

  override fun onCleared() {
    super.onCleared()
    if (lazyData.isInitialized()) {
      categoriesData.removeObserver(this)
    }
  }
}
