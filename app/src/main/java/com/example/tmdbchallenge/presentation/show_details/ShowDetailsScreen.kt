package com.example.tmdbchallenge.presentation.show_details

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tmdbchallenge.R
import com.example.tmdbchallenge.commons.Constants
import com.example.tmdbchallenge.domain.model.Show
import com.example.tmdbchallenge.presentation.destinations.EpisodeListScreenDestination
import com.example.tmdbchallenge.ui.composable.RatingStars
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
@Destination
fun ShowDetailsScreen(
    show: Show,
    navigator: DestinationsNavigator,
    viewModel: ShowDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.55f)
        ) {
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                imageModel = "${Constants.API_IMAGE_URL}/w400${show.thumbnailUrl}",
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillHeight,
                    alignment = Alignment.Center
                )
            )
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.BottomStart)
                    .padding(start = 16.dp, end = 16.dp, bottom = 40.dp)
            ) {
                Text(text = show.originalName, style = MaterialTheme.typography.headlineSmall)
                Text(text = show.name, style = MaterialTheme.typography.headlineLarge)
            }
            RatingStars(
                modifier = Modifier
                    .align(alignment = Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 16.dp),
                rating = show.rating / 2,
            )

            Image(painter = painterResource(id = R.drawable.ic_back), contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 16.dp, start = 16.dp)
                    .clickable {
                        navigator.navigateUp()
                    })
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.show_details_summary),
                    style = MaterialTheme.typography.titleLarge
                        .copy(color = Color(0xFF6243FF)),
                    modifier = Modifier.padding(top = 8.dp)
                )
                IconButton(onClick = {
                    viewModel.onEvent(ShowDetailsEvent.ToggleFavorite)
                }) {
                    Icon(
                        painter = painterResource(
                            id = if (state.show != null && state.show.isFavorite)
                                R.drawable.ic_favorite else R.drawable.ic_favorite_border
                        ),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = show.summary,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                state.seasons.forEach { season ->
                    SeasonItem(season = season,
                        modifier = Modifier.clickable {
                            if (season.episodeCount == 0) {
                                Toast.makeText(
                                    context,
                                    "There are no episodes yet",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                navigator.navigate(
                                    direction = EpisodeListScreenDestination(
                                        show = show,
                                        season = season
                                    ),
                                    onlyIfResumed = true
                                )
                            }
                        })
                }
            }
        }
    }


}
