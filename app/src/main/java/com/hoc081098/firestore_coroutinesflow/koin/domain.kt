package com.hoc081098.firestore_coroutinesflow.koin

import com.hoc081098.firestore_coroutinesflow.data.CategoryRepoImpl
import com.hoc081098.firestore_coroutinesflow.data.ImageRepoImpl
import com.hoc081098.firestore_coroutinesflow.domain.repo.CategoryRepo
import com.hoc081098.firestore_coroutinesflow.domain.repo.ImageRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val domainModule = module {
  single<CategoryRepo> { CategoryRepoImpl(get()) }

  single<ImageRepo> { ImageRepoImpl(get()) }
}