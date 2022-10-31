package com.example.tmdbchallenge.presentation.show_list

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
import androidx.compose.ui.unit.sp
import com.example.tmdbchallenge.commons.Constants.API_IMAGE_URL
import com.example.tmdbchallenge.domain.model.Show
import com.example.tmdbchallenge.ui.composable.RatingStars
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ShowListItem(
    modifier: Modifier = Modifier,
    show: Show
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primaryContainer),
    ) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.14f),
            imageModel = "$API_IMAGE_URL/w400${show.thumbnailUrl}",
            imageOptions = ImageOptions(
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.Center
            )
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 12.dp)
        ) {
            Text(
                text = show.name,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                RatingStars(rating = show.rating / 2)
                Text(
                    text = (show.rating / 2).toString(),
                    style = MaterialTheme.typography.titleSmall
                        .copy(fontSize = 14.sp, lineHeight = 20.sp)
                )
            }
        }

    }
}