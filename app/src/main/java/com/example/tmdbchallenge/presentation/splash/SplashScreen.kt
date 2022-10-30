package com.example.tmdbchallenge.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tmdbchallenge.R
import com.example.tmdbchallenge.presentation.destinations.LoginScreenDestination
import com.example.tmdbchallenge.presentation.destinations.ShowListScreenDestination
import com.example.tmdbchallenge.presentation.destinations.SplashScreenDestination
import com.example.tmdbchallenge.ui.theme.SplashGradientBottom
import com.example.tmdbchallenge.ui.theme.SplashGradientMid
import com.example.tmdbchallenge.ui.theme.SplashGradientTop
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
                    is SplashViewModel.SplashEvent.NavigateToList -> {
                        navigator.navigate(
                            direction = ShowListScreenDestination(),
                            onlyIfResumed = true
                        ) {
                            popUpTo(SplashScreenDestination.route) {
                                inclusive = true
                            }
                        }
                    }
                    is SplashViewModel.SplashEvent.NavigateToLogin -> {
                        navigator.navigate(
                            direction = LoginScreenDestination(),
                            onlyIfResumed = true
                        ) {
                            popUpTo(SplashScreenDestination.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SplashGradientTop,
                        SplashGradientMid,
                        SplashGradientBottom
                    )
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo_splash),
            modifier = Modifier
                .size(217.dp)
                .align(Alignment.Center),
            contentDescription = null
        )
    }
}