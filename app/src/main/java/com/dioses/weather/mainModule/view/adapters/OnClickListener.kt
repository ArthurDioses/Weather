package com.dioses.weather.mainModule.view.adapters

import com.dioses.weather.common.entities.Forecast

interface OnClickListener {
    fun onClick(forecast: Forecast)
}