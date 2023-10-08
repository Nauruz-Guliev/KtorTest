package ru.kpfu.itis.weatherktor.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.kpfu.itis.weatherktor.presentation.screens.weather.WeatherViewModel

val viewModelModule = module {
    viewModel { WeatherViewModel(get()) }
}
