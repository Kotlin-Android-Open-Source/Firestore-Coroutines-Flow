package com.hoc081098.firestore_coroutinesflow.domain.repo

import com.hoc081098.firestore_coroutinesflow.domain.entity.Category
import com.hoc081098.firestore_coroutinesflow.domain.entity.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepo {
  fun imagesByCategory(category: Category): Flow<List<Image>>
}