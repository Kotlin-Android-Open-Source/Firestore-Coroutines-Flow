package com.hoc081098.firestore_coroutinesflow

import android.app.Application
import com.hoc081098.firestore_coroutinesflow.koin.dataModule
import com.hoc081098.firestore_coroutinesflow.koin.domainModule
import com.hoc081098.firestore_coroutinesflow.koin.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
@ExperimentalCoroutinesApi
class App : Application() {
  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidLogger()
      androidContext(this@App)
      modules(
        dataModule,
        domainModule,
        viewModelModule,
      )
    }
  }
}