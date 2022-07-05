package ru.netology.nmedia

import kotlin.math.round

object Servis {

    fun formatKM (number: Long): String{

        when (number){
            in 0..999 -> return number.toString()
            in 1_000.. 1_099 -> return "${ round(number.toDouble() / 1_000).toInt()} K"
            in 1_100.. 9_999 -> return "${Math.round((number.toDouble() / 1_000)*10.0)/10.0} K"
            in 10_000.. 999_999 -> return "${round(number.toDouble() / 1_000).toInt()} K"
            else -> return  "${round(number.toDouble() / 1_000_000).toInt()} M"

        }
    }
}