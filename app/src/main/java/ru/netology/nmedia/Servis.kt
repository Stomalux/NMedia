package ru.netology.nmedia

import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.round

object Servis {

    fun formatKM (number: Long): String{


        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING

        when (number){
            in 0..999 -> return number.toString()
            in 1_000.. 1_099 -> return "${ round(number.toDouble() / 1_000).toInt()} K1"
            in 1_100.. 9_999 -> return "${Math.round((number.toDouble() / 1_000)*10.0)/10.0} K2"

            in 10_000.. 999_999 -> return "${round(number.toDouble() / 1_000).toInt()} K2"

           // in 10_000..999_999 ->return "${ df.format(number.toDouble() / 1_000).toInt()} K3"

            else -> return  "${round(number.toDouble() / 1_000_000).toInt()} M"

        }

      //  println(df.format(number))

    }



}