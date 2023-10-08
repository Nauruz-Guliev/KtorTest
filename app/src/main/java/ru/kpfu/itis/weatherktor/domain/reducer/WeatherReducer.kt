package ru.kpfu.itis.weatherktor.domain.reducer

import com.arkivanov.mvikotlin.core.store.Reducer
import ru.kpfu.itis.weatherktor.domain.store.WeatherStore
import ru.kpfu.itis.weatherktor.domain.store.WeatherStoreFactory

class WeatherReducer : Reducer<WeatherStore.State, WeatherStoreFactory.Message> {

    override fun WeatherStore.State.reduce(msg: WeatherStoreFactory.Message): WeatherStore.State =
        when (msg) {
            is WeatherStoreFactory.Message.SetError -> copy(
                isError = true,
                isLoading = false,
                exception = msg.error,
                data = null
            )

            is WeatherStoreFactory.Message.SetWeatherInfo -> copy(
                data = msg.data,
                isLoading = false,
                isError = false,
                exception = null
            )
            is WeatherStoreFactory.Message.SetLoading -> copy(
                isLoading = true,
                isError = false,
                data = null,
                exception = null
            )
        }
}
