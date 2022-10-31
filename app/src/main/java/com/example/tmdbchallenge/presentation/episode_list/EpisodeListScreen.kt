package com.example.tmdbchallenge.presentation.episode_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tmdbchallenge.R
import com.example.tmdbchallenge.domain.model.Season
import com.example.tmdbchallenge.domain.model.Show
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun EpisodeListScreen(
    show: Show,
    season: Season,
    navigator: DestinationsNavigator,
    viewModel: EpisodeListViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = season.name,
                        style = MaterialTheme.typography.titleLarge
                            .copy(color = Color.White)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.navigateUp()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                })
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                if (season.summary.isNotEmpty()) {
                    Text(
                        text = season.summary,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    state.episodes.forEach { episode ->
                        EpisodeItem(episode = episode)
                    }
                }
            }
        }
    }
}
