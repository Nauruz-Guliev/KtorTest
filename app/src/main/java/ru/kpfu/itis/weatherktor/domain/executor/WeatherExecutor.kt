package ru.kpfu.itis.weatherktor.domain.executor

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kpfu.itis.weatherktor.domain.repository.WeatherRepository
import ru.kpfu.itis.weatherktor.domain.store.WeatherStore
import ru.kpfu.itis.weatherktor.domain.store.WeatherStoreFactory
import kotlin.coroutines.CoroutineContext

class WeatherExecutor(
    private val dispatcher: CoroutineContext,
    private val repository: WeatherRepository,
) : CoroutineExecutor<WeatherStore.Intent, Nothing, WeatherStore.State, WeatherStoreFactory.Message, Nothing>() {

    override fun executeIntent(
        intent: WeatherStore.Intent,
        getState: () -> WeatherStore.State
    ) {
        scope.launch {
            when (intent) {
                is WeatherStore.Intent.LoadWeather -> loadWeatherInfo(intent.city)
            }
        }
    }

    private suspend fun loadWeatherInfo(city: String) = withContext(context = dispatcher) {
        dispatch(WeatherStoreFactory.Message.SetLoading)
        runCatching {
            val weatherResult = repository.getWeather(city)
            dispatch(WeatherStoreFactory.Message.SetWeatherInfo(weatherResult))
        }.onFailure { exception ->
            dispatch(WeatherStoreFactory.Message.SetError(exception))
        }
    }
}
