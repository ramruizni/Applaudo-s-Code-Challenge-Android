package com.example.tmdbchallenge.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.tmdbchallenge.R

@Composable
fun ImageErrorPlaceholder() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val indicator = createRef()
        Image(
            painter = painterResource(id = R.drawable.ic_tv),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            contentDescription = null,
            modifier = Modifier.constrainAs(indicator) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            contentScale = ContentScale.Fit
        )
    }
}