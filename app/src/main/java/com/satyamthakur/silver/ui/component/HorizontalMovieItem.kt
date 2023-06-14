package com.satyamthakur.silver.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.satyamthakur.silver.R
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.domain.model.PopularMovies
import com.satyamthakur.silver.ui.screen.dashboard.movie.MovieScreen
import com.satyamthakur.silver.ui.theme.MerryWeather
import com.satyamthakur.silver.ui.theme.SilverTheme
import com.satyamthakur.silver.ui.theme.StarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorizontalMovieItem(
    movie: Movie,
    onCLicked: (Movie) -> Unit
) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable { onCLicked.invoke(movie) }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(
                        stringResource(
                            id = R.string.w_original_poster,
                            movie.posterPath
                        )
                    )
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(
                    id = R.string.poster_description,
                    movie.title
                ),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .width(125.dp)
                    .aspectRatio(2 / 3F)
                    .clip(RoundedCornerShape(4.dp))
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontFamily = MerryWeather
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .width(125.dp)
                        .padding(horizontal = 4.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .padding(bottom = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = StarColor,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = stringResource(
                            id = R.string.vote_average_template,
                            movie.voteAverage
                        ),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = MerryWeather,
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.5F
                            )
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHorizontalMovieItem() {
    val movie by remember {
        mutableStateOf(PopularMovies[0])
    }
    SilverTheme {
        HorizontalMovieItem(
            movie = movie,
            onCLicked = {
                
            }
        )
    }
}
