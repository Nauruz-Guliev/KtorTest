package ru.kpfu.itis.weatherktor.data.service

import ru.kpfu.itis.weatherktor.data.dto.WeatherDto

interface WeatherService {

    suspend fun getWeather(city: String): WeatherDto
}
