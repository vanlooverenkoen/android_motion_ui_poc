package com.example.motionuipoc.util

object HeroHelper {
    private const val CAR_TRANSITION_NAME_PREFIX = "car"
    private const val CARD_TRANSITION_NAME_PREFIX = "card"

    fun getCarHero(position: Int): String = CAR_TRANSITION_NAME_PREFIX + position

    fun getCardHero(position: Int): String = CARD_TRANSITION_NAME_PREFIX + position

}