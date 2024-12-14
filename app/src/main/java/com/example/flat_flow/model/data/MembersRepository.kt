package com.example.flat_flow.model.data

import com.example.flat_flow.AppSession
import com.example.flat_flow.domain.Members
import com.example.flat_flow.model.data.api.ApiService

class MembersRepository (private val apiService: ApiService) {
    suspend fun fetchMembers(): List<Members> {
        val idRepublica = AppSession.userSession.idRepublica
            ?: throw IllegalStateException("Republica não definida")
        return apiService.getMembers(idRepublica)
    }
}