package com.satyamthakur.silver.ui.screen.dashboard.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.satyamthakur.silver.R
import com.satyamthakur.silver.ui.theme.MerryWeather

@Composable
fun SectionSeparator(
    sectionTitle: String,
    onSeeMoreClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 12.dp
            )
    ) {
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = MerryWeather,
                fontWeight = FontWeight.Black
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.5F),
                    shape = RoundedCornerShape(100.dp)
                )
                .clip(RoundedCornerShape(100.dp))
                .clickable { onSeeMoreClick.invoke() }
        ) {
            Text(
                text = stringResource(R.string.see_more),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Light,
                    fontFamily = MerryWeather,
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.5F
                    )
                ),
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                )
            )
        }
    }
}
