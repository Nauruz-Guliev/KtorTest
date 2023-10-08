package ru.kpfu.itis.weatherktor.presentation.screens.weather

import ru.kpfu.itis.weatherktor.domain.model.WeatherModel
import ru.kpfu.itis.weatherktor.domain.store.WeatherStore

fun WeatherModel.toUiModel() = WeatherUiModel(
    id = id,
    name = name,
    humidity = humidity,
    pressure = pressure,
    temperature = temperature,
    sunrise = sunrise,
    sunset = sunset,
    windSpeed = windSpeed,
    weatherList = weatherList.map {
        WeatherUiModel.Weather(
            description = it.description,
            icon = it.icon,
            id = it.id,
            main = it.main
        )
    }
)

fun WeatherStore.State.mapToUiState(): WeatherUiState = WeatherUiState(
    data = data?.toUiModel(),
    isLoading = isLoading,
    isError = isError,
    exception = exception
)
