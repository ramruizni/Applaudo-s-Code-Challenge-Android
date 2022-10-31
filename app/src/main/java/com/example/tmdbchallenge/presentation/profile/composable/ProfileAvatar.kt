package com.example.tmdbchallenge.presentation.profile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tmdbchallenge.R

@Composable
fun ProfileAvatar(
    name: String?,
    socialNetwork: String?,
    imageModel: Any? = painterResource(id = R.drawable.ic_account_circle),
    onEditPress: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(102.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile_ellipse),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiary),
                contentDescription = null,
                modifier = Modifier
                    .size(102.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.Fit,
            )
            Image(
                painter = imageModel as Painter,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                contentDescription = null,
                modifier = Modifier
                    .size(72.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.Fit,
            )
            IconButton(
                onClick = {
                    onEditPress()
                },
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (!socialNetwork.isNullOrEmpty()) {
            Text(
                text = name!!,
                style = MaterialTheme.typography.titleMedium
                    .copy(textAlign = TextAlign.Center)
            )
            Text(
                text = "@$socialNetwork",
                style = MaterialTheme.typography.bodyLarge
                    .copy(textAlign = TextAlign.Center)
            )
        } else {
            Text(
                text = stringResource(id = R.string.profile_empty_info),
                style = MaterialTheme.typography.bodyLarge
                    .copy(textAlign = TextAlign.Center)
            )
        }
    }
}