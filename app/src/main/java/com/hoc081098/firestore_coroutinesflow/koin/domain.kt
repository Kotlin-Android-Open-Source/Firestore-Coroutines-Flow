package com.hoc081098.firestore_coroutinesflow.koin

import com.hoc081098.firestore_coroutinesflow.data.CategoryRepoImpl
import com.hoc081098.firestore_coroutinesflow.domain.repo.CategoryRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val domainModule = module {
  single<CategoryRepo> { CategoryRepoImpl(get()) }
}