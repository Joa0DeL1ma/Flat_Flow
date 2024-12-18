package com.example.flat_flow.model.data

import com.example.flat_flow.AppSession
import com.example.flat_flow.domain.CleaningCards
import com.example.flat_flow.model.data.api.ApiService

class CleaningCardRepository(private val apiService: ApiService) {
    suspend fun fetchCleaningCards(): List<CleaningCards> {
        val idRepublica = AppSession.userSession.idRepublica
            ?: throw IllegalStateException("Republica não definida")
        return apiService.getCleaningCards(idRepublica)
    }
}