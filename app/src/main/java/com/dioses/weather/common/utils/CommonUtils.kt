package com.dioses.weather.common.utils

import java.text.SimpleDateFormat
import java.util.Locale

object CommonUtils {
    fun getHour(
        epoch: Long
    ) = getFormatedTime(epoch, "HH:mm")

    private fun getFormatedTime(epoch: Long, pattern: String): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(epoch * 1_000)
    }
}