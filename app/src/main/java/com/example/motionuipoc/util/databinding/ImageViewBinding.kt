package com.example.motionuipoc.util.databinding

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ImageViewBinding {
    @JvmStatic
    @BindingAdapter("imagesRes")
    fun bindAsyncImage(imageView: ImageView, @DrawableRes imagesRes: Int) {
        if (imagesRes == 0) {
            Picasso.get().cancelRequest(imageView)
            imageView.setImageDrawable(null)
            return
        }

        Picasso.get()
            .load(imagesRes)
            .fit()
            .centerInside()
            .into(imageView)
    }
}