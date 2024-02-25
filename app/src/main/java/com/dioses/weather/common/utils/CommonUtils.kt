package com.dioses.weather.common.utils

import com.dioses.weather.common.entities.Weather
import java.text.SimpleDateFormat
import java.util.Locale

object CommonUtils {
    fun getHour(
        epoch: Long
    ) = getFormatedTime(epoch, "HH:mm")

    fun getFullDate(
        epoch: Long
    ) = getFormatedTime(epoch, "dd/MM/yy HH:mm")

    private fun getFormatedTime(epoch: Long, pattern: String): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(epoch * 1_000)
    }

    fun getWeatherMain(weather: List<Weather>?): String {
        return if (!weather.isNullOrEmpty()) weather[0].main
        else "-"
    }

    fun getWeatherDescription(weather: List<Weather>?): String {
        return if (!weather.isNullOrEmpty()) weather[0].description
        else "-"
    }
}