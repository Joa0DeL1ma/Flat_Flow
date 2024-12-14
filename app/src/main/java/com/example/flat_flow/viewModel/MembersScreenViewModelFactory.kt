package com.example.flat_flow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flat_flow.domain.FetchMembersUseCase

class MembersScreenViewModelFactory(
    private val fetchMembersUseCase: FetchMembersUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MembersScreenViewModel::class.java)) { // Corrected line
            @Suppress("UNCHECKED_CAST")
            return MembersScreenViewModel(fetchMembersUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}