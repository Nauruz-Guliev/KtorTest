package ru.kpfu.itis.weatherktor.presentation.screens.weather

import com.arkivanov.mvikotlin.core.binder.Binder
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import ru.kpfu.itis.weatherktor.domain.store.WeatherStore
import ru.kpfu.itis.weatherktor.presentation.base.BaseViewModel

class WeatherViewModel(
    private val store: WeatherStore,
) : BaseViewModel<WeatherStore.Intent, WeatherStore.State, WeatherUiState, Nothing>(store = store) {

    override val _state : MutableStateFlow<WeatherUiState> = MutableStateFlow(WeatherUiState())
    val state = _state.asStateFlow()

    init {
        initBinder()
    }

    override fun initBinder() {
        binder = bind(Dispatchers.Main.immediate) {
            store.states.map {
                it.mapToUiState()
            } bindTo (::acceptState)
        }
        binder?.start()
    }

    fun loadWeather(city: String) {
        store.accept(WeatherStore.Intent.LoadWeather(city))
    }
}
