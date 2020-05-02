package com.hoc081098.firestore_coroutinesflow.koin

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val dataModule = module {
  single { FirebaseFirestore.getInstance() }
}