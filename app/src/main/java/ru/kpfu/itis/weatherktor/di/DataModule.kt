package ru.kpfu.itis.weatherktor.di

import org.koin.dsl.module
import ru.kpfu.itis.weatherktor.data.repository.WeatherRepositoryImpl
import ru.kpfu.itis.weatherktor.data.service.WeatherService
import ru.kpfu.itis.weatherktor.data.service.WeatherServiceKtorImpl
import ru.kpfu.itis.weatherktor.data.service.WeatherServiceRetrofitImpl
import ru.kpfu.itis.weatherktor.domain.repository.WeatherRepository

/*
    здесь меняем, какую реализацию хотим использовать (retrofit or ktor)
 */
val dataModule = module {
    factory<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<WeatherService> { WeatherServiceKtorImpl(get()) }
    // single<WeatherService> { get<WeatherServiceRetrofitImpl>() }
}
