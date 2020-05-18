package com.hoc081098.firestore_coroutinesflow

import android.content.Context
import android.content.res.Configuration.ORIENTATION_PORTRAIT

val Context.isOrientationPortrait get() = this.resources.configuration.orientation == ORIENTATION_PORTRAIT