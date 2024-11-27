package com.example.flat_flow.domain

data class CleaningCards(
    val numberDay: Int,
    val dayOfTheWeek: String?,
    val recurrence: String,
    val assigned: String,
    val task: String
)
