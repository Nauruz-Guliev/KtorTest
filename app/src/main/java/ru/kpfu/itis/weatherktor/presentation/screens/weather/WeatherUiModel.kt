package ru.kpfu.itis.weatherktor.presentation.screens.weather

import kotlin.coroutines.suspendCoroutine

data class WeatherUiModel(
    val id: Int,
    val name: String,
    val humidity: Int,
    val pressure: Int,
    val temperature: Double,
    val sunrise: Int,
    val sunset: Int,
    val windSpeed: Double,
    val weatherList: List<Weather>
) {
    data class Weather(
        val description: String,
        val icon: String,
        val id: Int,
        val main: String
    )
}
