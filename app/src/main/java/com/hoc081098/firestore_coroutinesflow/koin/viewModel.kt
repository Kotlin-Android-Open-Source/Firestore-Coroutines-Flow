package com.hoc081098.firestore_coroutinesflow.koin

import com.hoc081098.firestore_coroutinesflow.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val viewModelModule = module {
  viewModel { MainViewModel(get()) }
}