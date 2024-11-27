package com.example.flat_flow.model.data

import com.example.flat_flow.domain.BulletinCards
import com.example.flat_flow.model.data.api.ApiService


class BulletinCardRepository(private val apiService: ApiService) {
    suspend fun fetchBulletinCards(): List<BulletinCards> {
        return apiService.getBulletinCards()
    }
}
