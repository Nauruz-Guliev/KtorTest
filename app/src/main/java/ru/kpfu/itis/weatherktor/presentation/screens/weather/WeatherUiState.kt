package ru.kpfu.itis.weatherktor.presentation.screens.weather


data class WeatherUiState(
    val data: WeatherUiModel? = null,
    val exception: Throwable? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
