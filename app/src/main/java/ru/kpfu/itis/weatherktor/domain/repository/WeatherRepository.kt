package ru.kpfu.itis.weatherktor.domain.repository

import ru.kpfu.itis.weatherktor.domain.model.WeatherModel

interface WeatherRepository {

    suspend fun getWeather(cityName: String): WeatherModel
}
