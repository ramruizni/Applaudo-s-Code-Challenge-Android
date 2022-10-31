package com.example.tmdbchallenge.presentation.show_list


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tmdbchallenge.R
import com.example.tmdbchallenge.presentation.destinations.ProfileScreenDestination
import com.example.tmdbchallenge.presentation.destinations.ShowDetailsScreenDestination
import com.example.tmdbchallenge.ui.composable.SearchFilters
import com.example.tmdbchallenge.ui.composable.SearchTextField
import com.example.tmdbchallenge.ui.composable.box.EmptyListBox
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun ShowListScreen(
    navigator: DestinationsNavigator,
    viewModel: ShowListViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Scaffold(
        topBar = {
            if (state.showNameQuery == null) TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.show_list_title),
                        style = MaterialTheme.typography.titleLarge
                            .copy(color = Color.White)
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navigator.navigate(
                            direction = ProfileScreenDestination(),
                            onlyIfResumed = true
                        )
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_profile),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                    IconButton(onClick = {
                        viewModel.onEvent(ShowListEvent.QueryChanged(query = ""))
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            )
            else TopAppBar(
                title = {
                    SearchTextField(
                        text = state.showNameQuery,
                        onValueChange = {
                            viewModel.onEvent(ShowListEvent.QueryChanged(it))
                        },
                        placeholder = stringResource(id = R.string.show_list_query_placeholder),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.onEvent(ShowListEvent.QueryChanged(query = null))
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            )
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
        ) {

            val gridState = rememberLazyGridState()
            val coroutineScope = rememberCoroutineScope()
            val firstVisibleIndex by remember {
                derivedStateOf { gridState.firstVisibleItemIndex }
            }

            if (firstVisibleIndex > 0) {
                viewModel.onEvent(ShowListEvent.OnFirstVisibleListIndex(firstVisibleIndex))
            }

            if (state.shows.isEmpty()) {
                EmptyListBox(text = stringResource(R.string.show_list_empty))
            }

            Spacer(modifier = Modifier.height(24.dp))
            SearchFilters(
                currentFilter = state.showFilter,
                onTap = {
                    viewModel.onEvent(ShowListEvent.FilterChanged(it))
                    coroutineScope.launch {
                        gridState.animateScrollToItem(0)
                    }
                })
            Spacer(modifier = Modifier.height(24.dp))

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
                                    navigator.navigate(
                                        direction = ShowDetailsScreenDestination(show = show),
                                        onlyIfResumed = true
                                    )
                                },
                            show = show
                        )
                    }
                }
            }
        }
    }
}