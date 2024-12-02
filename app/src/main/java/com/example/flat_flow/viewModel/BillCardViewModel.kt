package com.example.flat_flow.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flat_flow.domain.BillCards
import com.example.flat_flow.domain.FetchBillCardsUseCase
import kotlinx.coroutines.launch

class BillCardViewModel(private val fetchBillCardsUseCase: FetchBillCardsUseCase) : ViewModel() {
    private val _billCards = mutableStateOf<List<BillCards>>(emptyList())
    val billCards: State<List<BillCards>> = _billCards

    init {
        loadBillCards()
    }

    private fun loadBillCards() {
        viewModelScope.launch {
            try {
                val cards = fetchBillCardsUseCase()
                Log.d("BillCardViewModel", "Cards carregados: $cards") // Log com TAG
                _billCards.value = cards
            } catch (e: Exception) {
                Log.e(
                    "BillCardViewModel",
                    "Erro ao carregar os dados: ${e.message}",
                    e
                ) // Log de erro com TAG
            }
        }
    }
}