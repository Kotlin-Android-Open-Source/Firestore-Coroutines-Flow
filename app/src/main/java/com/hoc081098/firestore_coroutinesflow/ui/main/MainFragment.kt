package com.hoc081098.firestore_coroutinesflow.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hoc081098.firestore_coroutinesflow.*
import com.hoc081098.firestore_coroutinesflow.databinding.MainFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainFragment : Fragment(R.layout.main_fragment) {
  private val vm by viewModel<MainViewModel>()
  private val binding by viewBinding { MainFragmentBinding.bind(it) }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val categoryAdapter = CategoryAdapter(GlideApp.with(this)) { category ->
      MainFragmentDirections
        .actionMainFragmentToCategoryDetailFragment(category)
        .let { findNavController().navigate(it) }
    }

    binding.recyclerView.run {
      setHasFixedSize(true)
      layoutManager = GridLayoutManager(context, if (context.isOrientationPortrait) 2 else 3)
      adapter = categoryAdapter
    }

    bindVM(categoryAdapter)
  }

  private fun bindVM(categoryAdapter: CategoryAdapter) {
    vm.categoriesData.observe(owner = viewLifecycleOwner) { state ->
      when (state) {
        Lce.Loading -> {
          binding.progressBar.isVisible = true
          binding.emptyError.isGone = true
        }
        is Lce.Content -> {
          binding.run {
            progressBar.isGone = true
            emptyError.isVisible = state.content.isEmpty()
            emptyError.text = if (state.content.isEmpty()) "Empty list" else null
          }
          categoryAdapter.submitList(state.content)
        }
        is Lce.Error -> {
          binding.run {
            progressBar.isGone = true
            emptyError.isVisible = true
            emptyError.text = "Error occurred: ${state.exception.message}"
          }
        }
      }
    }
  }
}
