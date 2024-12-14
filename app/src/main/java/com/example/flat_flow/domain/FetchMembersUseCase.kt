package com.example.flat_flow.domain

import com.example.flat_flow.model.data.MembersRepository

class FetchMembersUseCase(private val repository: MembersRepository) {
    suspend operator fun invoke(): List<Members> {
        return repository.fetchMembers()
    }
}