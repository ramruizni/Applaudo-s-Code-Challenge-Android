package com.example.tmdbchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.tmdbchallenge.presentation.splash.NavGraphs
import com.example.tmdbchallenge.ui.theme.TMDBChallengeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import es.dmoral.toasty.Toasty

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Prevents multiple toasts from queuing
        Toasty.Config.getInstance().allowQueue(false).apply()

        setContent {
            TMDBChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}