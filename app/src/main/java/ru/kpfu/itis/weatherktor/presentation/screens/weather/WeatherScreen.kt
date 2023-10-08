package ru.kpfu.itis.weatherktor.presentation.screens.weather

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import ru.kpfu.itis.weatherktor.R
import ru.kpfu.itis.weatherktor.presentation.base.composables.InfoCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel = koinViewModel()) {
    val text = remember { mutableStateOf(TextFieldValue("")) }
    val weatherDataState = viewModel.state.collectAsState()
    Scaffold(
        containerColor = colorResource(id = R.color.blue_light),
        topBar = {
            Column(
                modifier = Modifier.padding(
                    paddingValues = PaddingValues(
                        horizontal = dimensionResource(id = R.dimen.size_4),
                        vertical = dimensionResource(
                            id = R.dimen.size_4
                        )
                    )
                )
            ) {
                TextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.size_8)),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_4)),
                    trailingIcon = {
                        if (!weatherDataState.value.isLoading) {
                            Icon(Icons.Filled.Search, "Search city", Modifier.clickable {
                                viewModel.loadWeather(text.value.text)
                            })
                        } else {
                            CircularProgressIndicator(
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorResource(id = R.color.white_half_transparent),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text(text = stringResource(id = R.string.search_place_holder)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Ascii,
                        imeAction = ImeAction.Search
                    ),
                )
            }
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        Box(
            Modifier
                .padding(paddingValues = paddingValues)
                .scrollable(state = scrollState, orientation = Orientation.Vertical)
        ) {
            val currentState by remember { mutableStateOf(weatherDataState) }
            Crossfade(targetState = currentState, label = "State animation") {
                with(it.value) {
                    if (isError) ErrorStateScreen(exception = exception!!) {
                        viewModel.loadWeather(text.value.text)
                    }
                    else if (isLoading) {
                    } else if (data == null) {
                    } else {
                        SuccessStateScreen(data = data)
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorStateScreen(exception: Throwable, reloadWeatherCallback: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.red)),
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(R.drawable.baseline_error_outline_24),
                    contentDescription = ""
                )
                exception.message?.let { errorMessage ->
                    Text(
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.size_4)),
                        text = errorMessage,
                        fontSize = TextUnit(
                            value = dimensionResource(id = R.dimen.font_size_medium).value,
                            type = TextUnitType.Sp
                        ),
                        color = colorResource(id = R.color.blue_dark),
                        textAlign = TextAlign.Center
                    )
                }
                Button(onClick = { reloadWeatherCallback() }) {
                    Text(
                        text = stringResource(id = R.string.try_again),
                        color = colorResource(id = R.color.blue_light)
                    )
                }
            }
        }
    }
}

@Composable
fun SuccessStateScreen(data: WeatherUiModel) {
    Column {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterVertically),
                model = stringResource(id = R.string.icon_link, data.weatherList.first().icon),
                contentDescription = null,
            )
            Text(
                text = stringResource(id = R.string.temperature_value, data.temperature.toInt()),
                fontSize = TextUnit(
                    dimensionResource(id = R.dimen.font_size_extra_large).value,
                    TextUnitType.Sp
                ),
                color = colorResource(id = R.color.blue_dark),
            )
        }
        Row {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = data.weatherList.first().description,
                fontSize = TextUnit(
                    dimensionResource(id = R.dimen.font_size_small).value,
                    TextUnitType.Sp
                ),
                color = colorResource(id = R.color.blue_dark),
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.size_4))
        ) {
            Box(modifier = Modifier.weight(1f)) {
                InfoCard(
                    key = stringResource(id = R.string.humidity),
                    value = data.humidity.toString()
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                InfoCard(
                    key = stringResource(id = R.string.pressure),
                    value = data.pressure.toString()
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(top = dimensionResource(id = R.dimen.size_4))
        ) {
            InfoCard(
                key = stringResource(id = R.string.wind_speed_key),
                value = stringResource(id = R.string.wind_speed_value, data.windSpeed.toFloat())
            )
        }
    }
}
