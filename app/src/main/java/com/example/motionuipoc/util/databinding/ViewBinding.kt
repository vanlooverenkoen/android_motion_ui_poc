package com.example.motionuipoc.util.databinding

import android.view.View
import androidx.databinding.BindingAdapter

object ViewBinding {
    @JvmStatic
    @BindingAdapter("transitionName")
    fun bindTransitionName(view: View, transitionName: String?) {
        view.transitionName = transitionName
    }
}