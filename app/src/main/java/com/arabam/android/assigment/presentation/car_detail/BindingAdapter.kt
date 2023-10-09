package com.arabam.android.assigment.presentation.car_detail

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
@BindingAdapter("imageUrl")
fun ImageView.loadImageUrlWithCoil(url: String?) {
    this.load(url?.replace("{0}", "800x600")) {
        crossfade(true)
        crossfade(500)
        transformations(RoundedCornersTransformation(30f))
    }
}
