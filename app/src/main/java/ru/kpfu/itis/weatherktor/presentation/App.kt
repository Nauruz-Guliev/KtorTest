package ru.kpfu.itis.weatherktor.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.kpfu.itis.weatherktor.di.dataModule
import ru.kpfu.itis.weatherktor.di.domainModule
import ru.kpfu.itis.weatherktor.di.ktorClientModule
import ru.kpfu.itis.weatherktor.di.retrofitServiceImplModule
import ru.kpfu.itis.weatherktor.di.utilsModule
import ru.kpfu.itis.weatherktor.di.viewModelModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencies()
    }

    private fun initDependencies() {
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(applicationContext)
            modules(
                listOf(
                    utilsModule,
                    dataModule,
                    ktorClientModule,
                    retrofitServiceImplModule,
                    domainModule,
                    viewModelModule
                )
            )
        }
    }
}
