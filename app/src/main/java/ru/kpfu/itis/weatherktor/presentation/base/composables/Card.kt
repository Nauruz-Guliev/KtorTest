package ru.kpfu.itis.weatherktor.presentation.base.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import ru.kpfu.itis.weatherktor.R


@Composable
fun InfoCard(key: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(all = dimensionResource(id = R.dimen.size_4)),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white_half_transparent)
        )
    ) {
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
            BoxText(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = key,
            )
            BoxText(
                modifier = Modifier
                    .align(Alignment.CenterVertically), text = value,
                fontColor = R.color.orange
            )
        }
    }
}

