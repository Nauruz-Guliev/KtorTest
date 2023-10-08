package ru.kpfu.itis.weatherktor.domain.store

import com.arkivanov.mvikotlin.core.store.Store
import ru.kpfu.itis.weatherktor.domain.model.WeatherModel

interface WeatherStore : Store<WeatherStore.Intent, WeatherStore.State, Nothing> {

    data class State(
        val data: WeatherModel? = null,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val exception: Throwable? = null
    )

    sealed class Intent {

        data class LoadWeather(val city: String) : Intent()
    }
}
