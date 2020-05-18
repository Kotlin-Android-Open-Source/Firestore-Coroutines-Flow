package com.hoc081098.firestore_coroutinesflow.ui.category_detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.hoc081098.firestore_coroutinesflow.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@ExperimentalCoroutinesApi
class CategoryDetailFragment : Fragment(R.layout.category_detail_fragment) {
  private val args by navArgs<CategoryDetailFragmentArgs>()
  private val vm by viewModel<CategoryDetailViewModel> { parametersOf(args.category) }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    vm.categoriesData.observe(owner = viewLifecycleOwner) {
      Log.d("@@@", "Lce $it")
    }
  }
}