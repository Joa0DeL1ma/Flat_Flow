package com.example.flat_flow.domain

import com.example.flat_flow.model.data.BillCardRepository


class FetchBillCardsUseCase(private val repository: BillCardRepository) {
    suspend operator fun invoke(): List<BillCards> {
        return repository.fetchBillCards()
    }
}