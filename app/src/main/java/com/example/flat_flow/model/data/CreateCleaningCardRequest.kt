package com.example.flat_flow.model.data

data class CreateCleaningCardRequest(
    val numberDay: String?,
    val dayOfTheWeek: String?,
    val recurrence: String,
    val assigned: String,
    val task: String,
    val republicId: String?
)