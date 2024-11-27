package com.example.flat_flow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flat_flow.domain.FetchBulletinCardsUseCase

class BulletinCardViewModelFactory(
    private val fetchBulletinCardsUseCase: FetchBulletinCardsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BulletinCardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BulletinCardViewModel(fetchBulletinCardsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}