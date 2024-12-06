package com.example.flat_flow.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DeleteCleaningCardViewModel() : ViewModel() {
    private val _clickableCleaningCard = mutableStateOf(false)
    val clickableCleaningCard: State<Boolean> = _clickableCleaningCard

    fun toggleClickableCleaningCard() {
        _clickableCleaningCard.value = !_clickableCleaningCard.value
    }
}

