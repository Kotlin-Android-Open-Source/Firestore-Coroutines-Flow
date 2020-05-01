package com.hoc081098.firestore_coroutinesflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.hoc081098.firestore_coroutinesflow.databinding.MainActivityBinding
import kotlin.LazyThreadSafetyMode.NONE

class MainActivity : AppCompatActivity() {
  private val binding by lazy(NONE) { MainActivityBinding.inflate(layoutInflater) }
  private lateinit var appBarConfiguration: AppBarConfiguration

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    val navHostFragment =
        supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
    val navController = navHostFragment.navController

    setupActionBarWithNavController(
        navController,
        AppBarConfiguration(navController.graph).also { appBarConfiguration = it }
    )
  }

  override fun onSupportNavigateUp(): Boolean =
      findNavController(R.id.container).navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
}
