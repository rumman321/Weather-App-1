package com.app.weather_app

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)