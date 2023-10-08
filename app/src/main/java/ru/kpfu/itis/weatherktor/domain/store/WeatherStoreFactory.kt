package ru.kpfu.itis.weatherktor.domain.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import ru.kpfu.itis.weatherktor.domain.executor.WeatherExecutor
import ru.kpfu.itis.weatherktor.domain.model.WeatherModel
import ru.kpfu.itis.weatherktor.domain.reducer.WeatherReducer
import ru.kpfu.itis.weatherktor.domain.repository.WeatherRepository
import kotlin.coroutines.CoroutineContext

class WeatherStoreFactory(
    private val storeFactory: StoreFactory,
    private val ioDispatcher: CoroutineContext,
    private val repository: WeatherRepository,
) {

    fun create(): WeatherStore = object : WeatherStore,
        Store<WeatherStore.Intent, WeatherStore.State, Nothing> by
        storeFactory.create(
            name = WeatherStore::class.simpleName,
            initialState = WeatherStore.State(),
            bootstrapper = null,
            executorFactory = {
                WeatherExecutor(
                    dispatcher = ioDispatcher,
                    repository = repository
                )
            },
            reducer = WeatherReducer()
        ) {}

    sealed interface Message {

        data object SetLoading : Message
        data class SetWeatherInfo(val data: WeatherModel) : Message
        data class SetError(val error: Throwable) : Message
    }
}
