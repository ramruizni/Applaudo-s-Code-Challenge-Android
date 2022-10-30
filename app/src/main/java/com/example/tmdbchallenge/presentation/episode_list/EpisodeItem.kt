package com.example.tmdbchallenge.presentation.episode_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.tmdbchallenge.commons.Constants
import com.example.tmdbchallenge.domain.model.Episode
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun EpisodeItem(
    episode: Episode
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GlideImage(
            modifier = Modifier
                .weight(3.2f)
                .aspectRatio(0.76f),
            imageModel = "${Constants.API_IMAGE_URL}/w400${episode.stillPath}",
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
        )
        Column(
            modifier = Modifier
                .weight(6.8f)
                .padding(16.dp)
        ) {
            Text(
                text = episode.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${episode.runtime}m",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color(0xFF6243FF)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = episode.summary,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}