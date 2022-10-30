package com.example.tmdbchallenge.presentation.show_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tmdbchallenge.R
import com.example.tmdbchallenge.ui.composable.box.EmptyListBox
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun ShowListScreen(
    navigator: DestinationsNavigator,
    viewModel: ShowListViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Scaffold(

    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
        ) {

            val gridState = rememberLazyGridState()
            val firstVisibleIndex by remember {
                derivedStateOf { gridState.firstVisibleItemIndex }
            }

            if (firstVisibleIndex > 0) {
                viewModel.onEvent(ShowListEvent.OnFirstVisibleListIndex(firstVisibleIndex))
            }

            if (state.shows.isEmpty()) {
                EmptyListBox(text = stringResource(R.string.show_list_empty))
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyVerticalGrid(
                    state = gridState,
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(state.shows.size) { index ->
                        val show = state.shows[index]
                        ShowListItem(
                            modifier = Modifier
                                .clickable {
                                    /*
                                    navigator.navigate(
                                        direction = ShowDetailsScreenDestination(entry = entry),
                                        onlyIfResumed = true
                                    )
                                     */
                                },
                            show = show
                        )
                    }
                }
            }
        }
    }
}