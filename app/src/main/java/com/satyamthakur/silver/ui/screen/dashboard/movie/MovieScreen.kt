package com.satyamthakur.silver.ui.screen.dashboard.movie

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.satyamthakur.silver.R
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.domain.model.PopularMovies
import com.satyamthakur.silver.domain.model.cast.Cast
import com.satyamthakur.silver.ui.screen.dashboard.viewmodel.DashboardViewModel
import com.satyamthakur.silver.ui.theme.CategoryBackground
import com.satyamthakur.silver.ui.theme.CategoryTextColor
import com.satyamthakur.silver.ui.theme.DarkBlueColor
import com.satyamthakur.silver.ui.theme.StarColor
import com.satyamthakur.silver.utility.Resource
import org.jetbrains.annotations.Async
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MovieScreen(
    movie: Movie,
) {

    val viewModel = koinViewModel<DashboardViewModel>()
    val creditsData by viewModel.credits.collectAsStateWithLifecycle()

    var cast by remember { mutableStateOf <List<Cast>>(emptyList()) }

    LaunchedEffect(
        key1 = creditsData,
        block = {
            viewModel.getCreditsData(movie.id)
            cast = creditsData.data!!.cast
        }
    )

    Scaffold(

        topBar = {
            TopAppBar(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Transparent
                ),
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_more),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }
            )
        }

    ) {
        MoviePoster(
            castList = cast,
            movie = movie
        )
    }

}

@Composable
fun MoviePoster(
    castList: List<Cast>,
    movie: Movie
) {

    val lazyListState = rememberLazyListState()
    var scrolledY = 0f
    var previousOffset = 0f

    LazyColumn(
        Modifier
            .fillMaxSize(),
        lazyListState
    ){
        item {
            Image(
                painter = painterResource(id = R.drawable.mock_poster),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .graphicsLayer {
                        scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                        translationY = scrolledY * 0.5f
                        previousOffset = lazyListState.firstVisibleItemScrollOffset.toFloat()
                    }
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f),
            )
        }
        items(1){
            Column(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                    )
                    .padding(
                        horizontal = 20.dp,
                        vertical = 20.dp
                    )
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = movie.title,
                        maxLines = 2,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth())
                    IconButton(onClick = {  }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bookmark),
                            contentDescription = null,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
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
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.5f
                            ),
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(top = 15.dp)
                ) {
                    for (genre in movie.genresID){
                        Text(
                            text = genre.toString(),
                            modifier = Modifier
                                .background(color = CategoryBackground, shape = RoundedCornerShape(20)),
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = CategoryTextColor
                            ),
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = stringResource(id = R.string.length),
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.5f
                                )
                            ),
                            fontSize = 12.sp
                        )
                        Text(
                            text = movie.id.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 12.sp
                        )
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = stringResource(id = R.string.language),
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.5f
                                )
                            ),
                            fontSize = 12.sp
                        )
                        Text(
                            text = movie.originalLanguage,
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 12.sp
                        )
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = stringResource(id = R.string.rating),
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.5f
                                )
                            ),
                            fontSize = 12.sp
                        )
                        Text(
                            text = movie.id.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 12.sp
                        )
                    }
                }
                Text(
                    text = stringResource(id = R.string.description),
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = DarkBlueColor
                    ),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = movie.overview,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.5f
                        )
                    ),
                    fontSize = 14.sp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(id = R.string.cast),
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = DarkBlueColor
                        ),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(id = R.string.see_more),
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.5f
                            )
                        ),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .border(
                                BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(0.5f)),
                                shape = RoundedCornerShape(50)
                            )
                            .padding(horizontal = 8.dp, vertical = 5.dp)

                    )
                }
                CastMovie(castList = castList)
            }
        }
    }
}

@Composable
fun CastMovie(
    castList: List<Cast>
){
    LazyRow(
        modifier = Modifier
        .padding(top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        items(castList.size){ index ->
            Column(
                modifier = Modifier.width(100.dp)
            ) {
                AsyncImage(
                    model = castList[index].profilePath,
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .clip(RoundedCornerShape(15.dp))
                )
                Text(
                    text = castList[index].name,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = DarkBlueColor
                    ),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}