package com.example.flat_flow.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DeleteBillCardViewModel: ViewModel() {
    private val _clickableBillCard = mutableStateOf(false)
    val clickableBillCard: State<Boolean> = _clickableBillCard

    fun toggleClickableBillCard() {
        _clickableBillCard.value = !_clickableBillCard.value
    }
}

