package ru.kpfu.itis.weatherktor.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.koin.dsl.module
import ru.kpfu.itis.weatherktor.BuildConfig


val networkModule = module {
    single {
        HttpClient(Android) {
            defaultRequest {
                url {
                    host = BuildConfig.BASE_URL
                    parameters.apply {
                        append("appid", BuildConfig.API_KEY)
                        append("units", "metric")
                    }
                }
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}
