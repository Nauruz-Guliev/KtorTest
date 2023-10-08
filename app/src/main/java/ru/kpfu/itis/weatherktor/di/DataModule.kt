package ru.kpfu.itis.weatherktor.di

import org.koin.dsl.module
import ru.kpfu.itis.weatherktor.data.repository.WeatherRepositoryImpl
import ru.kpfu.itis.weatherktor.data.service.WeatherService
import ru.kpfu.itis.weatherktor.data.service.WeatherServiceImpl
import ru.kpfu.itis.weatherktor.domain.repository.WeatherRepository

val dataModule = module {
    factory<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<WeatherService> { WeatherServiceImpl(get()) }
}
