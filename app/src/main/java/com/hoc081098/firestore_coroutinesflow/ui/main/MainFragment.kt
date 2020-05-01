package com.hoc081098.firestore_coroutinesflow.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.hoc081098.firestore_coroutinesflow.R

class MainFragment : Fragment(R.layout.main_fragment) {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    FirebaseFirestore.getInstance()
      .collection("categories")
      .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
        Log.d("###", "${querySnapshot?.count()}")
      }
  }
}
