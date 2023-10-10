package ru.kpfu.itis.weatherktor.data.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import ru.kpfu.itis.weatherktor.data.dto.WeatherDto

class WeatherServiceKtorImplTest : BehaviorSpec({

    Given("Ktor client") {
        val client = WeatherServiceKtorImpl(HttpClient(
            engine = MockEngine { request ->
                respond(
                    content = ByteReadChannel("""$TEST_JSON"""),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        ) {
            install(ContentNegotiation) {
                json(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                })
            }
        })
        When("making request") {
            val weatherResult: WeatherDto = client.getWeather(TEST_CITY)
            Then("successful result is returned") {
                val expectedResult: WeatherDto = getExpectedModel()
                weatherResult shouldBeEqual expectedResult
            }
        }
    }
}) {

    private companion object {

        fun getExpectedModel() = WeatherDto(
            base = "stations",
            clouds = WeatherDto.Clouds(
                99
            ),
            coord = WeatherDto.Coord(
                55.7887, 49.1221
            ),
            cod = 200,
            dt = 1696754936,
            id = 551487,
            name = "Kazan’",
            timezone = 10800,
            visibility = 10000,
            weather = listOf(
                WeatherDto.Weather(
                    description = "overcast clouds",
                    icon = "04d",
                    id = 804,
                    main = "Clouds"
                )
            ),
            sys = WeatherDto.Sys(
                country = "RU",
                id = 48937,
                sunrise = 1696733934,
                sunset = 1696773794,
                type = 2
            ),
            main = WeatherDto.Main(
                feelsLike = 277.26,
                grndLevel = 975,
                humidity = 64,
                pressure = 982,
                seaLevel = 982,
                temp = 281.24,
                tempMax = 281.49,
                tempMin = 278.56
            ),
            wind = WeatherDto.Wind(
                256, 13.8, 8.3
            )
        )

        const val TEST_CITY = "Kazan"
        const val TEST_JSON =
            "{\"coord\":{\"lon\":49.1221,\"lat\":55.7887},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":281.24,\"feels_like\":277.26,\"temp_min\":278.56,\"temp_max\":281.49,\"pressure\":982,\"humidity\":64,\"sea_level\":982,\"grnd_level\":975},\"visibility\":10000,\"wind\":{\"speed\":8.3,\"deg\":256,\"gust\":13.8},\"clouds\":{\"all\":99},\"dt\":1696754936,\"sys\":{\"type\":2,\"id\":48937,\"country\":\"RU\",\"sunrise\":1696733934,\"sunset\":1696773794},\"timezone\":10800,\"id\":551487,\"name\":\"Kazan’\",\"cod\":200}"
    }
}
