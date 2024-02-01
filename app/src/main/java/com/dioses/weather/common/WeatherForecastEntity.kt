package com.dioses.weather.common

data class WeatherForecastEntity(
    val timeZone: String,
    val current: Current,
    val hourly: List<Forecast>
)