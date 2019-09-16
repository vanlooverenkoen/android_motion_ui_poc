package com.example.motionuipoc.model

import androidx.annotation.DrawableRes

class Car(
    val model: String,
    val quote: String,
    val manufacturer: String,
    @DrawableRes val carImage: Int,
    @DrawableRes val manufacturerLogo: Int,
    val description: String
)