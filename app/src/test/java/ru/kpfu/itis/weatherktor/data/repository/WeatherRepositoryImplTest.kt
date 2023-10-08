package ru.kpfu.itis.weatherktor.data.repository

import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ru.kpfu.itis.weatherktor.data.service.WeatherService

class WeatherRepositoryImplTest: BehaviorSpec( {
    val service = mockk<WeatherService>()
    val repository = WeatherRepositoryImpl(weatherService = service)
})
