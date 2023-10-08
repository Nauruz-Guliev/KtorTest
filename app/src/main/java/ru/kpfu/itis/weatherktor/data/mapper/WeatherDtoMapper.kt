package ru.kpfu.itis.weatherktor.data.mapper

import ru.kpfu.itis.weatherktor.data.dto.WeatherDto
import ru.kpfu.itis.weatherktor.domain.model.WeatherModel


fun WeatherDto.toWeatherModel(): WeatherModel =
    WeatherModel(
        id = id,
        name = name,
        humidity = main.humidity,
        pressure = main.pressure,
        temperature = main.temp,
        sunrise = sys.sunrise,
        sunset = sys.sunset,
        windSpeed = wind.speed,
        weatherList = weather.map {
            WeatherModel.Weather(
                description = it.description,
                icon = it.icon,
                main = it.main,
                id = it.id
            )
        }
    )
