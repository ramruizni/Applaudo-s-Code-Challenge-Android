package com.example.tmdbchallenge.ui.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tmdbchallenge.R

@Composable
fun RatingStars(
    modifier: Modifier = Modifier,
    rating: Double = 3.5
) {
    val starEmpty = painterResource(id = R.drawable.star_empty)
    val starHalf = painterResource(id = R.drawable.star_half)
    val starFull = painterResource(id = R.drawable.star_full)

    Row(modifier = modifier) {
        Icon(
            painter = if (rating < 0.5) starEmpty else if (rating < 1.0) starHalf else starFull,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outlineVariant
        )
        Spacer(modifier = Modifier.width(2.dp))
        Icon(
            painter = if (rating < 1.5) starEmpty else if (rating < 2.0) starHalf else starFull,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outlineVariant
        )
        Spacer(modifier = Modifier.width(2.dp))
        Icon(
            painter = if (rating < 2.5) starEmpty else if (rating < 3.0) starHalf else starFull,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outlineVariant
        )
        Spacer(modifier = Modifier.width(2.dp))
        Icon(
            painter = if (rating < 3.5) starEmpty else if (rating < 4.0) starHalf else starFull,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outlineVariant
        )
        Spacer(modifier = Modifier.width(2.dp))
        Icon(
            painter = if (rating < 4.5) starEmpty else if (rating < 5.0) starHalf else starFull,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outlineVariant
        )
    }
}