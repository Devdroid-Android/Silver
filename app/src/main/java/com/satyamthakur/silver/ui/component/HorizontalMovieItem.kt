package com.satyamthakur.silver.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.satyamthakur.silver.R
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.domain.model.PopularMovies
import com.satyamthakur.silver.ui.theme.SilverTheme
import com.satyamthakur.silver.ui.theme.StarColor

@Composable
fun HorizontalMovieItem(
    movie: Movie,
    onCLicked: (Movie) -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable { onCLicked.invoke(movie) }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = stringResource(id = R.string.w_original_poster, movie.posterPath),
                contentDescription = "Poster for ${movie.title}",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .width(150.dp)
                    .aspectRatio(3 / 5F)
                    .clip(RoundedCornerShape(4.dp))
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 14.sp,
                        lineHeight = 16.sp
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.width(150.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = StarColor,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = "${movie.voteAverage}/10 IMDb",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.5F
                            ),
                            fontSize = 12.sp
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
            onCLicked = {}
        )
    }
}