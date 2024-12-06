package com.example.flat_flow.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DeleteBulletinCardViewModel: ViewModel() {
    private val _clickableBulletinCard = mutableStateOf(false)
    val clickableBulletinCard: State<Boolean> = _clickableBulletinCard

    fun toggleClickableBulletinCard() {
        _clickableBulletinCard.value = !_clickableBulletinCard.value
    }
}

