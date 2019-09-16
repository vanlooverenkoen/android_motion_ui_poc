package com.example.motionuipoc.util.databinding

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ViewBinding {
    @JvmStatic
    @BindingAdapter("transitionName")
    fun bindTransitionName(view: View, transitionName: String) {
        view.transitionName = transitionName
    }
}