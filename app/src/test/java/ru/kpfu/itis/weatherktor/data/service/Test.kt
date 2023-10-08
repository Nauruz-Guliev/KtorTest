package ru.kpfu.itis.weatherktor.data.service


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Test(
    @SerialName("base")
    val base: String, // stations
    @SerialName("clouds")
    val clouds: Clouds,
    @SerialName("cod")
    val cod: Int, // 200
    @SerialName("coord")
    val coord: Coord,
    @SerialName("dt")
    val dt: Int, // 1696754936
    @SerialName("id")
    val id: Int, // 551487
    @SerialName("main")
    val main: Main,
    @SerialName("name")
    val name: String, // Kazan’
    @SerialName("sys")
    val sys: Sys,
    @SerialName("timezone")
    val timezone: Int, // 10800
    @SerialName("visibility")
    val visibility: Int, // 10000
    @SerialName("weather")
    val weather: List<Weather>,
    @SerialName("wind")
    val wind: Wind
) {
    @Serializable
    data class Clouds(
        @SerialName("all")
        val all: Int // 99
    )

    @Serializable
    data class Coord(
        @SerialName("lat")
        val lat: Double, // 55.7887
        @SerialName("lon")
        val lon: Double // 49.1221
    )

    @Serializable
    data class Main(
        @SerialName("feels_like")
        val feelsLike: Double, // 277.26
        @SerialName("grnd_level")
        val grndLevel: Int, // 975
        @SerialName("humidity")
        val humidity: Int, // 64
        @SerialName("pressure")
        val pressure: Int, // 982
        @SerialName("sea_level")
        val seaLevel: Int, // 982
        @SerialName("temp")
        val temp: Double, // 281.24
        @SerialName("temp_max")
        val tempMax: Double, // 281.49
        @SerialName("temp_min")
        val tempMin: Double // 278.56
    )

    @Serializable
    data class Sys(
        @SerialName("country")
        val country: String, // RU
        @SerialName("id")
        val id: Int, // 48937
        @SerialName("sunrise")
        val sunrise: Int, // 1696733934
        @SerialName("sunset")
        val sunset: Int, // 1696773794
        @SerialName("type")
        val type: Int // 2
    )

    @Serializable
    data class Weather(
        @SerialName("description")
        val description: String, // overcast clouds
        @SerialName("icon")
        val icon: String, // 04d
        @SerialName("id")
        val id: Int, // 804
        @SerialName("main")
        val main: String // Clouds
    )

    @Serializable
    data class Wind(
        @SerialName("deg")
        val deg: Int, // 256
        @SerialName("gust")
        val gust: Double, // 13.8
        @SerialName("speed")
        val speed: Double // 8.3
    )
}
