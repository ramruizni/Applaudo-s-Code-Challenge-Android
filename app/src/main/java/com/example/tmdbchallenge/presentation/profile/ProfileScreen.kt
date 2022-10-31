package com.example.tmdbchallenge.presentation.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tmdbchallenge.R
import com.example.tmdbchallenge.presentation.destinations.LoginScreenDestination
import com.example.tmdbchallenge.presentation.destinations.ShowDetailsScreenDestination
import com.example.tmdbchallenge.presentation.destinations.ShowListScreenDestination
import com.example.tmdbchallenge.presentation.profile.composable.EditProfileDialog
import com.example.tmdbchallenge.presentation.profile.composable.LogoutDialog
import com.example.tmdbchallenge.presentation.profile.composable.ProfileAvatar
import com.example.tmdbchallenge.presentation.profile.edit_profile.EditProfileEvent
import com.example.tmdbchallenge.presentation.profile.edit_profile.EditProfileViewModel
import com.example.tmdbchallenge.presentation.show_list.ShowListItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun ProfileScreen(
    navigator: DestinationsNavigator,
    viewModel: ProfileViewModel = hiltViewModel(),
    editViewModel: EditProfileViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    val editState = editViewModel.state

    val rowState = rememberLazyListState()

    val context = LocalContext.current
    LaunchedEffect(context) {
        viewModel
            .event
            .collect { event ->
                when (event) {
                    is ProfileEvent.OnLogoutDialogPress -> {
                        if (event.submit) {
                            navigator.navigate(
                                direction = LoginScreenDestination(),
                                onlyIfResumed = true
                            ) {
                                popUpTo(ShowListScreenDestination.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                    else -> Unit
                }
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.profile_title),
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
                }
            )
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            ProfileAvatar(
                name = editState.savedName,
                socialNetwork = editState.savedSocialNetwork
            ) {
                editViewModel.onEvent(EditProfileEvent.ShowEditDialog)
            }
            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.profile_my_favorites),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (state.favorites.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(216.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.favorites_list_empty),
                            style = MaterialTheme.typography.headlineMedium
                                .copy(
                                    color = MaterialTheme.colorScheme.primary,
                                    textAlign = TextAlign.Center
                                ),
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                        )
                    }
                } else {
                    LazyRow(
                        state = rowState,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        items(state.favorites.size) { index ->
                            val favorite = state.favorites[index]
                            Box(modifier = Modifier.width(156.dp)) {
                                ShowListItem(
                                    modifier = Modifier
                                        .clickable {
                                            navigator.navigate(
                                                direction = ShowDetailsScreenDestination(show = favorite),
                                                onlyIfResumed = true
                                            )
                                        },
                                    show = favorite
                                )
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    viewModel.onEvent(ProfileEvent.ShowLogoutDialog)
                },
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = stringResource(R.string.profile_logout),
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }

        LogoutDialog(
            show = state.showLogoutDialog,
            onDismiss = {
                viewModel.onEvent(ProfileEvent.OnLogoutDialogPress(false))
            },
            onSubmit = {
                viewModel.onEvent(ProfileEvent.OnLogoutDialogPress(true))
            }
        )

        EditProfileDialog(
            show = editState.showEditDialog,
            name = editState.name,
            nameError = editState.nameError,
            socialNetwork = editState.socialNetwork,
            socialNetworkError = editState.socialNetworkError,
            onNameChange = {
                editViewModel.onEvent(EditProfileEvent.NameChanged(it))
            },
            onSocialNetworkChange = {
                editViewModel.onEvent(EditProfileEvent.SocialNetworkChanged(it))
            },
            onDismiss = {
                editViewModel.onEvent(EditProfileEvent.Dismiss)
            },
            onSubmit = {
                editViewModel.onEvent(EditProfileEvent.Submit)
            }
        )
    }
}