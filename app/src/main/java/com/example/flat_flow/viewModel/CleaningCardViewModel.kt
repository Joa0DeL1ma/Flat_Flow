package com.example.flat_flow.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.flat_flow.domain.CleaningCards
import com.example.flat_flow.domain.FetchCleaningCardsUseCase

class CleaningCardViewModel(private val fetchCleaningCardsUseCase: FetchCleaningCardsUseCase) : ViewModel() {
    private val _cleaningCards = mutableStateOf<List<CleaningCards>>(emptyList())
    val cleaningCards: State<List<CleaningCards>> = _cleaningCards

    init {
        loadCleaningCards()
    }

    private fun loadCleaningCards() {
        viewModelScope.launch {
            try {
                val cards = fetchCleaningCardsUseCase()
                Log.d("CleaningCardViewModel", "Cards carregados: $cards") // Log com TAG
                _cleaningCards.value = cards
            } catch (e: Exception) {
                Log.e("CleaningCardViewModel", "Erro ao carregar os dados: ${e.message}", e) // Log de erro com TAG
            }
        }
    }
}