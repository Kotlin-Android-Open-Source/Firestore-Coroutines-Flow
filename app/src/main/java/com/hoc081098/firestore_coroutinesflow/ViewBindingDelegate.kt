package com.hoc081098.firestore_coroutinesflow

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewBindingDelegate<T : ViewBinding>(
  private val fragment: Fragment,
  private val viewBindingFactory: (View) -> T,
) : ReadOnlyProperty<Fragment, T> {
  private var binding: T? = null

  init {
    fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
      override fun onCreate(owner: LifecycleOwner) {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
          Log.d("###", "$fragment::view::viewLifecycleOwnerLiveData")

          viewLifecycleOwner?.lifecycle?.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
              binding = null
              viewLifecycleOwner.lifecycle.removeObserver(this)
              Log.d("###", "$fragment::view::onDestroy")
            }
          })
        }
      }

      override fun onDestroy(owner: LifecycleOwner) {
        fragment.lifecycle.removeObserver(this)
      }
    })
  }

  override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
    binding?.let { return it }

    if (!fragment.viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
      error("Attempt to get view binding when fragment view is destroyed")
    }

    return viewBindingFactory(thisRef.requireView()).also { binding = it }
  }
}

fun <T : ViewBinding> Fragment.viewBinding(factory: (View) -> T) =
  ViewBindingDelegate<T>(this, factory)