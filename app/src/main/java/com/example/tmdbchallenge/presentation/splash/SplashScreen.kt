package com.example.tmdbchallenge.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun SplashScreen(
    navigator: DestinationsNavigator,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel
            .splashEvents
            .collect { event ->
                when (event) {
                    // TODO: Create Login and ShowList screens
                    is SplashViewModel.SplashEvent.NavigateToList -> {
                        /*navigator.navigate(
                            direction = ShowListDestination(),
                            onlyIfResumed = true
                        ) {
                            popUpTo(SplashScreenDestination.route) {
                                inclusive = true
                            }
                        }*/
                    }
                    is SplashViewModel.SplashEvent.NavigateToLogin -> {
                        /*navigator.navigate(
                            direction = LoginScreenDestination(),
                            onlyIfResumed = true
                        ) {
                            popUpTo(SplashScreenDestination.route) {
                                inclusive = true
                            }
                        }*/
                    }
                }
            }
    }
}