package com.example.tmdbchallenge.presentation.show_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.tmdbchallenge.commons.Constants
import com.example.tmdbchallenge.domain.model.Season
import com.example.tmdbchallenge.ui.composable.ImageErrorPlaceholder
import com.example.tmdbchallenge.ui.composable.ImageLoadingIndicator
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SeasonItem(
    modifier: Modifier = Modifier,
    season: Season
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GlideImage(
            modifier = Modifier
                .weight(3.2f)
                .aspectRatio(0.76f),
            imageModel = "${Constants.API_IMAGE_URL}/w400${season.posterUrl}",
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            ),
            loading = {
                ImageLoadingIndicator()
            },
            failure = {
                ImageErrorPlaceholder()
            }
        )
        Column(
            modifier = Modifier
                .weight(6.8f)
                .padding(16.dp)
        ) {
            Text(
                text = season.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${season.episodeCount} Episodes",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = season.summary,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}