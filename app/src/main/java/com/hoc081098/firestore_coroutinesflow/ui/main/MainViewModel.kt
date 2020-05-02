package com.hoc081098.firestore_coroutinesflow.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hoc081098.firestore_coroutinesflow.Lce
import com.hoc081098.firestore_coroutinesflow.domain.repo.CategoryRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

@ExperimentalCoroutinesApi
class MainViewModel(categoryRepo: CategoryRepo) : ViewModel() {
  val categoriesData = categoryRepo.watchCategories()
    .map { Lce.content(it) }
    .onStart { emit(Lce.loading()) }
    .catch { emit(Lce.error(it)) }
    .asLiveData()
}
