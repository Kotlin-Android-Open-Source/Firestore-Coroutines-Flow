package com.hoc081098.firestore_coroutinesflow.domain.repo

import com.hoc081098.firestore_coroutinesflow.domain.entity.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepo {
  fun watchCategories(): Flow<List<Category>>
}