package com.example.tmdbchallenge.ui.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ImageLoadingIndicator() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val indicator = createRef()
        CircularProgressIndicator(
            modifier = Modifier.constrainAs(indicator) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}