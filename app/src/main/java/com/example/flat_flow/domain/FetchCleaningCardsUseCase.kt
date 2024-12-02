package com.example.flat_flow.domain

import com.example.flat_flow.model.data.CleaningCardRepository

class FetchCleaningCardsUseCase(private val repository: CleaningCardRepository) {
    suspend operator fun invoke(): List<CleaningCards> {
        return repository.fetchCleaningCards()
    }
}