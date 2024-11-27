package com.example.flat_flow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.flat_flow.domain.BillCards
import com.example.flat_flow.domain.FetchBillCardsUseCase

class BillCardViewModel(private val fetchBillCardsUseCase: FetchBillCardsUseCase) : ViewModel() {
    private val _billCards = mutableStateOf<List<BillCards>>(emptyList())
    val billCards: State<List<BillCards>> = _billCards

    init {
        loadBillCards()
    }

    private fun loadBillCards() {
        viewModelScope.launch {
            try {
                _billCards.value = fetchBillCardsUseCase()
            } catch (e: Exception) {
                // Trate erros aqui
            }
        }
    }
}