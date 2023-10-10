package ru.kpfu.itis.weatherktor.data.repository

import ru.kpfu.itis.weatherktor.data.mapper.toWeatherModel
import ru.kpfu.itis.weatherktor.data.service.WeatherService
import ru.kpfu.itis.weatherktor.data.service.WeatherServiceRetrofitImpl
import ru.kpfu.itis.weatherktor.domain.model.WeatherModel
import ru.kpfu.itis.weatherktor.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherService: WeatherService
) : WeatherRepository {

    override suspend fun getWeather(cityName: String): WeatherModel =
        weatherService.getWeather(cityName).toWeatherModel()
}
