package com.example.motionuipoc.util

import com.example.motionuipoc.model.Car

object HeroHelper {
    private const val CAR_TRANSITION_NAME_PREFIX = "car_"
    private const val CARD_TRANSITION_NAME_PREFIX = "card_"
    private const val LOGO_TRANSITION_NAME_PREFIX = "logo_"
    private const val MORE_INFO_TRANSITION_NAME_PREFIX = "more_info_"

    @JvmStatic
    fun getCarImageHero(car: Car?): String? = car?.let { CAR_TRANSITION_NAME_PREFIX + it.id }

    @JvmStatic
    fun getLogoImageHero(car: Car?): String? = car?.let { LOGO_TRANSITION_NAME_PREFIX + it.id }

    @JvmStatic
    fun getCardHero(car: Car?): String? = car?.let { CARD_TRANSITION_NAME_PREFIX + it.id }

    @JvmStatic
    fun getMoreInfoHero(car: Car?): String? = car?.let { MORE_INFO_TRANSITION_NAME_PREFIX + it.id }

}