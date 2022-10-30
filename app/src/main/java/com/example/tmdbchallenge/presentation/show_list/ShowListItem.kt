package com.example.tmdbchallenge.presentation.show_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.tmdbchallenge.commons.Constants.API_IMAGE_URL
import com.example.tmdbchallenge.domain.model.Show
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ShowListItem(
    modifier: Modifier = Modifier,
    show: Show
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.41f)
            .clip(RoundedCornerShape(22.dp))
    ) {
        GlideImage(
            modifier = Modifier
                .width(110.dp)
                .height(100.dp)
                .align(Alignment.BottomEnd),
            imageModel = "$API_IMAGE_URL/w200${show.thumbnailUrl}",
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )
        )
        Column {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = show.name,
                style = MaterialTheme.typography.titleSmall
            )
        }

    }

}