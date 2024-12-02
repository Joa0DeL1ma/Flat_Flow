package com.example.flat_flow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flat_flow.domain.FetchCleaningCardsUseCase

class CleaningCardViewModelFactory(
    private val fetchCleaningCardsUseCase: FetchCleaningCardsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CleaningCardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CleaningCardViewModel(fetchCleaningCardsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}