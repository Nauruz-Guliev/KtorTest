package ru.kpfu.itis.weatherktor.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.arkivanov.mvikotlin.core.binder.Binder
import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.coroutines.flow.MutableStateFlow
import ru.kpfu.itis.weatherktor.presentation.screens.weather.WeatherUiState

abstract class BaseViewModel<in Intent : Any, out StoreState : Any, out UiState : Any, out Label : Any>(
    private val store: Store<Intent, StoreState, Label>
) : ViewModel() {

    protected var binder: Binder? = null

    protected abstract val _state: MutableStateFlow<@UnsafeVariance UiState>

    abstract fun initBinder()

    open fun acceptState(state:  @UnsafeVariance UiState) {
        _state.value = state
    }

    override fun onCleared() {
        super.onCleared()
        binder?.stop()
        store.dispose()
    }
}
