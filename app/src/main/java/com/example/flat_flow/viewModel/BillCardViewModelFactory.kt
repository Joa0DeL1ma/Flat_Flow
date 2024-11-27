package com.example.flat_flow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flat_flow.domain.FetchBillCardsUseCase


class BillCardViewModelFactory(
    private val fetchBillCardsUseCase: FetchBillCardsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BillCardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BillCardViewModel(fetchBillCardsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}