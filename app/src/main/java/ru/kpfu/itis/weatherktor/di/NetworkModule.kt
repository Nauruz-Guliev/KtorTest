package ru.kpfu.itis.weatherktor.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import ru.kpfu.itis.weatherktor.BuildConfig
import ru.kpfu.itis.weatherktor.data.service.WeatherServiceRetrofitImpl


val ktorClientModule = module {
    single {
        HttpClient(Android) {
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTP
                    host = BuildConfig.BASE_URL
                    parameters.apply {
                        append("appid", BuildConfig.API_KEY)
                        append("units", "metric")
                    }
                }
            }
            install(Logging) {
                logger = Logger.ANDROID
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

val retrofitServiceImplModule = module {
    val apiKeyInterceptor = Interceptor { chain ->
        val modifiedUrl = chain.request().url.newBuilder().apply {
            addQueryParameter("appid", BuildConfig.API_KEY)
            addQueryParameter("units", "metric")
        }.build()
        chain.proceed(chain.request().newBuilder().url(modifiedUrl).build())
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor(apiKeyInterceptor)
        .build()

    single<WeatherServiceRetrofitImpl> {
        Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create()
    }
}
