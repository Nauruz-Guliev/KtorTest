package ru.kpfu.itis.weatherktor.presentation.screens.composables

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import ru.kpfu.itis.weatherktor.R

@Composable
fun BoxText(
    modifier: Modifier,
    text: String,
    @DimenRes fontSize: Int = R.dimen.font_size_medium,
    @ColorRes fontColor: Int = R.color.blue_dark
) {
    Text(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.size_5)),
        text = text,
        fontSize = dimensionResource(id = fontSize).value.sp,
        color = colorResource(id = fontColor)
    )
}
