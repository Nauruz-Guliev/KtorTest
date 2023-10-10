package ru.kpfu.itis.weatherktor.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import ru.kpfu.itis.weatherktor.data.dto.WeatherDto


class WeatherServiceKtorImpl(private val client: HttpClient) : WeatherService {

    override suspend fun getWeather(city: String): WeatherDto =
        client.get {
            url {
                path("data/2.5/weather")
                parameter("q", city)
            }
        }.body()
}
