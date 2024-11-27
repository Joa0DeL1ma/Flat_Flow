package com.example.flat_flow.domain

import com.example.flat_flow.model.data.BulletinCardRepository


class FetchBulletinCardsUseCase(private val repository: BulletinCardRepository) {
    suspend operator fun invoke(): List<BulletinCards> {
        return repository.fetchBulletinCards()
    }
}
