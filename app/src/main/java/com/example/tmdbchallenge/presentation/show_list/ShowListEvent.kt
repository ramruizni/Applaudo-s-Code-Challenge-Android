package com.example.tmdbchallenge.presentation.show_list

sealed class ShowListEvent {
    data class OnFirstVisibleListIndex(val index: Int) : ShowListEvent()
    object Logout : ShowListEvent()
}