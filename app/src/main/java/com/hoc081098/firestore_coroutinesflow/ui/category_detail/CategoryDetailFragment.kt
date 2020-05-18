package com.hoc081098.firestore_coroutinesflow.ui.category_detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.firebase.firestore.FirebaseFirestore
import com.hoc081098.firestore_coroutinesflow.R
import com.hoc081098.firestore_coroutinesflow.data.snapshots
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.json.JSONArray
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CategoryDetailFragment : Fragment(R.layout.category_detail_fragment) {
  private val args by navArgs<CategoryDetailFragmentArgs>()
  private val vm by viewModel<CategoryDetailViewModel> { parametersOf(args.category) }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

  }
}