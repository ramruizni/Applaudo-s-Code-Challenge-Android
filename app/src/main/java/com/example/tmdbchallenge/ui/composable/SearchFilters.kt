package com.example.tmdbchallenge.ui.composable

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tmdbchallenge.R
import com.example.tmdbchallenge.domain.ShowFilter

@Preview
@Composable
fun SearchFilters(
    modifier: Modifier = Modifier,
    currentFilter: ShowFilter = ShowFilter.TOP_RATED,
    onTap: (ShowFilter) -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        SingleFilter(
            name = stringResource(id = R.string.show_list_top_rated),
            isSelected = currentFilter == ShowFilter.TOP_RATED,
            onTap = { onTap(ShowFilter.TOP_RATED) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        SingleFilter(
            name = stringResource(id = R.string.show_list_popular),
            isSelected = currentFilter == ShowFilter.POPULAR,
            onTap = { onTap(ShowFilter.POPULAR) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        SingleFilter(
            name = stringResource(id = R.string.show_list_on_tv),
            isSelected = currentFilter == ShowFilter.ON_TV,
            onTap = { onTap(ShowFilter.ON_TV) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        SingleFilter(
            name = stringResource(id = R.string.show_list_airing_today),
            isSelected = currentFilter == ShowFilter.AIRING_TODAY,
            onTap = { onTap(ShowFilter.AIRING_TODAY) }
        )
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun SingleFilter(
    name: String = "Airing",
    isSelected: Boolean = false,
    filter: ShowFilter = ShowFilter.TOP_RATED,
    onTap: (ShowFilter) -> Unit,
) {
    Box(
        modifier = Modifier
            .border(width = 1.dp, color = Color.Black, shape = CircleShape)
            .clip(shape = CircleShape)
            .background(color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary)
            .clickable { onTap(filter) }
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.titleSmall.copy(
                color = if (isSelected) MaterialTheme.colorScheme.secondary else Color(
                    0xFF6B6B83
                ),
                lineHeight = 24.sp
            )
        )
    }
}