package ru.kpfu.itis.weatherktor.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.kpfu.itis.weatherktor.domain.store.WeatherStore
import ru.kpfu.itis.weatherktor.domain.store.WeatherStoreFactory

val domainModule = module {
    factory<StoreFactory> { DefaultStoreFactory() }
    factory<WeatherStore> {
        WeatherStoreFactory(
            storeFactory = get(),
            ioDispatcher = get(named(DispatcherQualifier.MAIN)),
            repository = get()
        ).create()
    }
}
