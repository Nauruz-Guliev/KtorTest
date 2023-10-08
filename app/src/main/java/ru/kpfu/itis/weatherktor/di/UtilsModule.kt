package ru.kpfu.itis.weatherktor.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val utilsModule = module {
    factory<CoroutineContext>(named(DispatcherQualifier.MAIN)) { Dispatchers.Main }
    factory<CoroutineContext>(named(DispatcherQualifier.IO)) { Dispatchers.IO }
}

enum class DispatcherQualifier {
    MAIN, IO, UNCONFINED, DEFAULT,
}
