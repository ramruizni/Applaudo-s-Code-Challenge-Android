package com.example.tmdbchallenge.presentation.login

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data class AcceptedTerms(val acceptedTerms: Boolean) : LoginEvent()

    object Submit: LoginEvent()
}