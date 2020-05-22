package com.hoc081098.firestore_coroutinesflow.ui.category_detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hoc081098.firestore_coroutinesflow.*
import com.hoc081098.firestore_coroutinesflow.databinding.CategoryDetailFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@ExperimentalCoroutinesApi
class CategoryDetailFragment : Fragment(R.layout.category_detail_fragment) {
  private val args by navArgs<CategoryDetailFragmentArgs>()
  private val vm by viewModel<CategoryDetailViewModel> { parametersOf(args.category) }
  private val binding by viewBinding { CategoryDetailFragmentBinding.bind(it) }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val imageAdapter = ImageAdapter(GlideApp.with(this)) { image ->
      Log.d("###", "Click $image")
    }

    binding.recyclerView.run {
      setHasFixedSize(true)
      layoutManager = StaggeredGridLayoutManager(
        if (context.isOrientationPortrait) 2 else 3,
        RecyclerView.VERTICAL,
      )
      adapter = imageAdapter
    }

    bindVM(imageAdapter)
  }

  private fun bindVM(adapter: ImageAdapter) {
    vm.imagesData.observe(owner = viewLifecycleOwner) { state ->
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
          adapter.submitList(state.content)
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