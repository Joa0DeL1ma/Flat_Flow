package com.example.flat_flow.model.data

import com.example.flat_flow.AppSession
import com.example.flat_flow.domain.BillCards
import com.example.flat_flow.model.data.api.ApiService

class BillCardRepository (private val apiService: ApiService) {
    suspend fun fetchBillCards(): List<BillCards> {
        val idRepublica = AppSession.userSession.idRepublica
            ?: throw IllegalStateException("Republica não definida")
        return apiService.getBillCards(idRepublica)
    }
}