package com.example.flat_flow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.flat_flow.domain.BulletinCards
import com.example.flat_flow.domain.FetchBulletinCardsUseCase

class BulletinCardViewModel(private val fetchBulletinCardsUseCase: FetchBulletinCardsUseCase) : ViewModel() {
    private val _bulletinCards = mutableStateOf<List<BulletinCards>>(emptyList())
    val bulletinCards: State<List<BulletinCards>> = _bulletinCards

    init {
        loadBulletinCards()
    }

    private fun loadBulletinCards() {
        viewModelScope.launch {
            try {
                _bulletinCards.value = fetchBulletinCardsUseCase()
            } catch (e: Exception) {
                // Trate erros aqui
            }
        }
    }
}
