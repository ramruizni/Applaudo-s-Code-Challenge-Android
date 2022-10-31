package com.example.tmdbchallenge.ui.composable.box

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun RetryBox(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(),
    text: String,
    onRetry: (() -> Unit)?
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
            .clickable {
                onRetry?.let { it() }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium
                .copy(
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                ),
            modifier = Modifier
                .padding(horizontal = 24.dp)
        )
    }
}