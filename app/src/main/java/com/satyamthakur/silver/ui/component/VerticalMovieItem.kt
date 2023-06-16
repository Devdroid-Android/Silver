package com.satyamthakur.silver.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.satyamthakur.silver.R
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.domain.model.PopularMovies
import com.satyamthakur.silver.ui.theme.CategoryBackground
import com.satyamthakur.silver.ui.theme.CategoryTextColor
import com.satyamthakur.silver.ui.theme.SilverTheme
import com.satyamthakur.silver.ui.theme.StarColor
import com.satyamthakur.silver.utility.getMovieGenreByGenreID

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun VerticalMovieItem(
    movie: Movie,
    onMovieClicked: (Movie) -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable { onMovieClicked(movie) }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = stringResource(
                    id = R.string.w_original_poster,
                    movie.posterPath
                ),
                contentDescription = stringResource(
                    id = R.string.poster_description,
                    movie.title
                ),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .width(100.dp)
                    .aspectRatio(3 / 5F)
                    .clip(RoundedCornerShape(4.dp))
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
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
                        text = stringResource(
                            id = R.string.vote_average_template,
                            movie.voteAverage.toString()
                        ),
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.5F
                            ),
                            fontSize = 12.sp
                        )
                    )
                }
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    maxItemsInEachRow = 3,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    movie.genresID.forEachIndexed { index, genreID ->
                        Box(
                            modifier = Modifier
                                .padding(top = if (index > 2) 8.dp else 0.dp)
                                .clip(RoundedCornerShape(100.dp))
                                .background(CategoryBackground)

                        ) {
                            Text(
                                text = getMovieGenreByGenreID(genreID).title,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 12.sp,
                                    color = CategoryTextColor
                                ),
                                modifier = Modifier.padding(
                                    horizontal = 12.dp,
                                    vertical = 4.dp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewVerticalMovieItem() {
    SilverTheme {
        Surface {
            VerticalMovieItem(
                movie = PopularMovies[0],
                onMovieClicked = {}
            )
        }
    }
}
