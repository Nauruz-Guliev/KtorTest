package ru.kpfu.itis.weatherktor.data.service

import retrofit2.http.GET
import retrofit2.http.Query
import ru.kpfu.itis.weatherktor.data.dto.WeatherDto

interface WeatherServiceRetrofitImpl: WeatherService {

    @GET("data/2.5/weather")
    override suspend fun getWeather(@Query("q") city: String) : WeatherDto
}
